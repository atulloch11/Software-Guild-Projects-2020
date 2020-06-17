$(document).ready(function() {

$('#cityName').hide();


$('#get-brewery').click(function(event) {
  $('#cityName').show();
  clearForms();

  var city = $('#add-name-city').val();
  $('#cityName').append(city);

  $.ajax ({
    type: 'GET',
    url: 'https://api.openbrewerydb.org/breweries?by_city=' + city,
    success: function(data, status) {
      $.each(data, function (index, brewery) {
        console.log(brewery.name);
        var name = brewery.name;
        var address = brewery.street + '<br>' + brewery.city + '<br>' + brewery.state + '<br>' + brewery.postal_code;
        var phone = brewery.phone;
        var type = brewery.brewery_type;
        var website = brewery.website_url;

        var row = '<tr>';
            row += '<td>' + name + '</td>';
            row += '<td>' + address + '</td>';
            row += '<td>' + 'Brewery Type: ' + type + '</td>';
            row += '<td>' + 'Phone Number: ' + phone +  '</td>';
            row += '<td>' + 'Website URL: ' + website + '</td>';
            row += '</tr><hr/>';
            $('#infoBrewery').append(row);
            
            
            
        var latitude = brewery.latitude;
        var longitude = brewery.longitude;
        
        initMap(latitude, longitude);
        
        
      });
  },
  error: function() {


  }

});

});
});


function clearForms() {
  $('#infoBrewery').empty();
  $('#add-name-city').empty();
  $('#cityName').empty();
}


function initMap(latitude, longitude) {
        var city = {lat: latitude, lng: longitude};
        var map = new google.maps.Map(
                $('#map'), {zoom: 15, center: city});
        var marker = new google.maps.Marker({position: city, map: map});
}
