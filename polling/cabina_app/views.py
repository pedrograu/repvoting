#encoding: utf-8
import json
from django.http import HttpResponse
from django.shortcuts import render
from rest_framework.decorators import api_view
from cabina_app.models import Vote, Question,  Poll
from cabina_app.services import save_vote, get_poll, get_user, vote_as_json, can_vote, get_key_rsa
import requests


@api_view(['GET'])
def recibe_id_votacion(request, id_poll):
    try:
        id_poll = int(id_poll)
    except:
        informacion = "Lo sentimos, el id de la votacion es incorrecto"
        return render(request, "informacion.html", {'informacion': informacion})

    if not can_vote(request, id_poll):
        informacion = "Usted no tiene acceso a esta votación"
        return render(request, "informacion.html", {'informacion': informacion})

    # if not verify_user(request):
    #     informacion = "El usuario es erroneo, autenticate de nuevo"
    #     return render(request, "informacion.html", {'informacion': informacion})

    poll = get_poll(id_poll)
    user = get_user(request)

    return render(request, "index.html", {'poll': poll,
                                          'user': user,
                                          'questions': poll.questions})


@api_view(['POST'])
def cabinarecepcion(request):
    if request.method == 'POST':
        post_data = request.POST

        id_poll = post_data['id_poll']
        poll = get_poll(id_poll)

        username = post_data['username']
        user = get_user(request)

        # if user.username != username or not verify_user():
        #     informacion = "El usuario es erroneo, autenticate de nuevo"
        #     return render(request, "informacion.html", {'informacion': informacion})

        if not can_vote(request, id_poll):
            informacion = "Usted no tiene acceso a esta votación"
            return render(request, "informacion.html", {'informacion': informacion})

        answers = ''
        for question in poll.questions:
            answer_question = post_data[str(question.id)]
            answers = answers + " " + question.text + ":" + str(answer_question) + ","
        answers = "{" + answers[:-1] + " }"

        vote = Vote()
        vote.id = 1
        vote.id_poll = poll.id
        vote.age = user.age
        vote.genre = user.genre
        vote.community = user.community
        vote.answers = answers

        save_vote(vote)
        json_vote = vote_as_json(vote)

        informacion = "Votacion guardada con éxito, por favor diríjase a la siguiente direccion:"
        visitar_web = True
        return render(request, "informacion.html", {'informacion': informacion, 'visitar_web': visitar_web,
                                                    'json_vote': json_vote})
    else:
        informacion = "Lo sentimos, el metodo solicitado no esta disponible"
        return render(request, "informacion.html", {'informacion': informacion})


# este metodo simula el devolver un voto
@api_view(['GET'])
def prueba_id_voto(request, id_voto):
    voto1 = Vote()
    voto1.id_voto = id_voto
    voto1.age = 20
    voto1.community = "Andalucia"
    voto1.genre = "HOMBRE"
    voto1.id_poll = id_voto
    voto1.answers = "¿Desea aprobar EGC?:SI"

    to_dump_voto = {
        'id': str(voto1.id),
        'id_poll': str(voto1.id_poll),
        'answers': voto1.answers,
        'age': str(voto1.age),
        'genre': voto1.genre,
        'community': voto1.community,
    }

    json_data = json.dumps(to_dump_voto)
    return HttpResponse(json_data, mimetype='application/json')


@api_view(['GET'])
def prueba_rsa(request, id_votacion):
    key = get_key_rsa(id_votacion)
    if key == False:
        informacion = "Hubo un problema con la clave RSA, contacte con verificación"
        return render(request, "informacion.html", {'informacion': informacion})

    json_data = json.dumps(key)
    return HttpResponse(json_data, mimetype='application/json')