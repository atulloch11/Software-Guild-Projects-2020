$(document).ready(function() {

  $('#instructionsDiv').hide();
  $('.row2').hide();



$('#submitCocktail').click(function(event) {
  $('#instructionsDiv').show();
  $('.row2').show();

  $('#cocktailDisplayName').empty();

  $('#cocktailInstructions').empty();

  $('#cocktailPictureDisplay').empty();

  $('#cocktailIngredients').empty();

  var cocktail = $('#cocktailName').val();
  $('#cocktailDisplayName').append(cocktail);

  $.ajax ({
    type: 'GET',
    url: 'https://www.thecocktaildb.com/api/json/v1/1/search.php?s=' + cocktail,
    success: function(data, status) {
      var instructions = data.drinks[0].strInstructions;
      $('#cocktailInstructions').append(instructions);

      var picture = data.drinks[0].strDrinkThumb;
      console.log(picture);
      $('#cocktailPictureDisplay').append('<img id="img" src="' + picture + '">');

      var row = '<tbody>';
          row += '<tr id="drinkInfo">';
          row += '<td>' + data.drinks[0].strIngredient1 + ": <br>" + data.drinks[0].strMeasure1 + ' <br></td><br>';
          row += '<td>' + data.drinks[0].strIngredient2 + ": <br>" + data.drinks[0].strMeasure2 + ' <br></td><br>';
          row += '<td>' + data.drinks[0].strIngredient3 + ": <br>" + data.drinks[0].strMeasure3 +' <br></td><br>';
          row += '<td>' + data.drinks[0].strIngredient4 + ": <br>" + data.drinks[0].strMeasure4 + ' <br></td><br>';
          row += '</tr>';
          row += '</tbody>';
    $('#cocktailIngredients').append(row);


    },
    error: function() {

    }





  });

});
});
