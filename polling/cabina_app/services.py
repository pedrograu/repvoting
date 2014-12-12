import json
import urllib
import urllib2
import requests
import rsa
from cabina_app.models import User, Poll


def verify_user(request):
    user = request.COOKIES.get('user')
    token = request.COOKIES.get('token')
    r = requests.get("URL", params={"user": user, "token": token})
    json_autenticacion = r.json()
    result = False
    if json_autenticacion['valid'] == "true":
        result = True
    return result


def can_vote(id_poll):
    r = requests.get("http://localhost:8080/ADMCensus/census/canVote.do?idVotacion=" + str(id_poll))
    json_censo = r.json()
    result = False
    if json_censo['result'] == "Yes":
        result = True
    return result


def save_vote(vote):
    # json_vote = vote_as_json(vote)
    # voto_cifrado = encrypt_rsa(json_vote,CLAVEAQUI)
    voto_cifrado = vote_as_json(vote)
    data = [('vote', voto_cifrado), ('votation_id', vote.id_poll)]
    data = urllib.urlencode(data)
    path = 'http://php-egc.rhcloud.com/vote.php'
    req = urllib2.Request(path, data)
    response = urllib2.urlopen(req)
    response_data = json.load(response)
    result = False
    if response_data['msg'] == u'1':
        result = True
    return result


def get_poll(id_poll):
    # r = requests.get('API/survey/getSurvey.do/id=' + str(id_poll))
    r = requests.get('http://localhost:8000/prueba_id_votacion/' + str(id_poll) + '/')
    json_poll = json.dumps(r.json())
    poll = json.loads(json_poll, object_hook=json_as_poll)
    return poll


def get_user(request):
    username = request.COOKIES.get('user')
    user = User()
    user.username = "juamaiosu"
    user.password = "123456789"
    user.email = "juamaiosu@us.es"
    user.age = 23
    user.community = "Andalucia"
    user.genre = "Hombre"
    # r = requests.get('URL DE AUTENTIFICACION)
    # URL = 'http://localhost:8000/prueba_id_votacion/' + str(id_votacion) + '/'
    # r = requests.get(URL)
    # json_bueno = json.dumps(r.json())
    # usuario_objeto = json.loads(json_bueno, object_hook=json_as_usuario)
    # # pprint.pprint(json_bueno)
    # # print (type(votacion_objeto))
    # # pprint.pprint(votacion_objeto.questions)
    # return usuario_objeto
    return user


def json_as_poll(json_poll):
    poll = Poll()
    poll.__dict__.update(json_poll)
    return poll


def vote_as_json(vote):

    to_dump_vote = {
        'id': vote.id,
        'id_poll': vote.id_poll,
        'age': vote.age,
        'genre': vote.genre,
        'community': vote.community,
        'answers': vote.answers
    }
    return json.dumps(to_dump_vote)


def encrypt_rsa(message, public_key_loc):
    crypto = rsa.encrypt(message, public_key_loc)
    return crypto


def decrypt_rsa(crypto, private_key):
    return rsa.decrypt(crypto, private_key)


def generate_rsa(bits=1024):
    (pubkey, privkey) = rsa.newkeys(bits)
    return pubkey, privkey