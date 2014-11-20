var cavControllers = angular.module("cavControllers", []);
cavControllers.controller('indexController', function($rootScope){
	$rootScope.title="Agora Voting"
});
cavControllers.controller('createController', function($scope, $http, $rootScope, $location){
	$rootScope.title="Crear votación";
	$scope.qs=[];
	$scope.i = 0;
	$scope.survey={type:"survey", usernameCreator:"admin", title:"",description:"",startDate:"",endDate:"",questions:[]};
	$scope.addQuestionField = function(){
		$scope.i+=1;
		$scope.qs.push($scope.i);
	};
	$scope.submit = function(survey){
		console.log(survey)
		var dat = null;
		$http.post("vote/save.do",survey).success(function(data,status){
			if (status=200){
				$http.get("/ADMCensus/census/create.do?idVotacion="+data.id+"&fecha_inicio="+data.startDate+"&fecha_fin="+data.endDate+"&tituloVotacion="+data.title).success(function(data,status){
					if (status=200){
						$location.path("/list");
					}
				});
			}
		});
		
		
		
		
	};
});
cavControllers.controller('listController', function($scope, $http, $rootScope){
	$rootScope.title = "Lista de votaciones";
	$http.get("vote/mine.do").success(function(data,status){
		$scope.surveys = data;
	});
	
	$scope.borrar = function(survey){
		$http.get("/ADMCensus/census/canDelete.do?idVotacion="+survey.id).success(function(data,status){
			if (data[0].result){
				
			}
		});
	}
	
	$scope.goEditCensus=function(survey){
		window.location="/ADMCensus/census/details.do?censusId="+survey.census;
	}
});