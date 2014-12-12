#encoding: utf-8
import json
from django.http import HttpResponse
from django.shortcuts import render
from rest_framework.decorators import api_view
from cabina_app.models import Vote, Question,  Poll, User
from cabina_app.services import save_vote, verify_user, can_vote, get_poll, get_user, vote_as_json


@api_view(['GET'])
def recibe_id_votacion(request, id_poll):
    try:
        id_poll = int(id_poll)
    except:
        informacion = "Lo sentimos, el id de la votacion es incorrecto"
        return render(request, "informacion.html", {'informacion': informacion})

    # if not verify_user(request) or not can_vote(id_poll):
    #     return render(request, "informacion.html")

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

        if user.username != username:
            informacion = "Lo sentimos, los usuarios son incorrectos, intentelo de nuevo mas tarde"
            return render(request, "informacion.html", {'informacion': informacion})

        # if not verify_user(request) or not can_vote(id_poll):
        #     return render(request, "informacion.html")

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
        token = request.COOKIES.get('token')
        return render(request, "informacion.html", {'informacion': informacion, 'visitar_web': visitar_web,
                                                    'token': token})
    else:
        informacion = "Lo sentimos, el metodo solicitado no esta disponible"
        return render(request, "informacion.html", {'informacion': informacion})


# este metodo simula el metodo que debe tener administracion de votaciones
@api_view(['GET'])
def prueba_id_votacion(request, id_votacion):
    votacion1 = Poll()
    votacion1.id = id_votacion
    votacion1.title = "El Referendum 74"
    votacion1.description = "Para legalizar el matrimonio entre personas del mismo sexo."
    votacion1.startDate = "01/11/2014"
    votacion1.endDate = "25/11/2014"
    #Creacion de preguntas manuales
    pregunta1 = Question()
    pregunta1.id = 1
    pregunta1.text = "Quieres que se legalice el matrimonio entre personas del mismo sexo"
    pregunta1.questions = votacion1

    pregunta2 = Question()
    pregunta2.id = 2
    pregunta2.text = "Te gusta la asignaruta de EGC"
    pregunta2.questions = votacion1

    to_dump_pregunta1 = {'id': pregunta1.id,
                         'text': pregunta1.text,
    }

    to_dump_pregunta2 = {'id': pregunta2.id,
                         'text': pregunta2.text,
    }
    #la lista, el json.dumps lo muestra perfectamente
    #list_dumps_preguntas = [to_dump_pregunta,to_dump_pregunta2]

    to_dump_votacion = {
        'id': votacion1.id,
        'title': votacion1.title,
        'description': votacion1.description,
        'startDate': votacion1.startDate,
        'endDate': votacion1.endDate,
        'questions': [to_dump_pregunta1, to_dump_pregunta2]
    }

    json_data = json.dumps(to_dump_votacion)
    return HttpResponse(json_data, mimetype='application/json')