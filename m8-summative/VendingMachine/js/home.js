  $(document).ready(function () {

    getAllVendingItems();

    selectItem();



    $('#addDollar').click(function(event) {
      $('#message').val("");
      $('#itemNumDisplay').val("");
      $('#changeDisplay').val("");
      $('#returnChange').show();
      addDollar();
    });


    $('#addQuarter').click(function(event) {
      $('#changeDisplay').val("");
      $('#message').val("");
      $('#itemNumDisplay').val("");
      $('#returnChange').show();
      addQuarter();
    });

    $('#addDime').click(function(event) {
      $('#changeDisplay').val("");
      $('#message').val("");
      $('#itemNumDisplay').val("");
      $('#returnChange').show();
      addDime();
    });


    $('#addNickel').click(function(event) {
      $('#changeDisplay').val("");
      $('#message').val("");
      $('#itemNumDisplay').val("");
      $('#returnChange').show();
      addNickel();
    });

    makePurchase();



    clickChange();


  });

  function addDollar() {
    var currentMoney = parseFloat($('#add-money').val());
    currentMoney += 1.00;

    $('#add-money').val(currentMoney.toFixed(2));
  }

  function addQuarter() {
    var currentMoney = parseFloat($('#add-money').val());

    var quarter = parseFloat(0.25);
    currentMoney += quarter;

    $('#add-money').val(currentMoney.toFixed(2));
  }

  function addDime() {
    var currentMoney = parseFloat($('#add-money').val());

    var dime = parseFloat(0.10);
    currentMoney += dime;

    $('#add-money').val(currentMoney.toFixed(2));
  }

  function addNickel() {
    var currentMoney = parseFloat($('#add-money').val());

    var nickel = parseFloat(0.05);
    currentMoney += nickel;

    $('#add-money').val(currentMoney.toFixed(2));
  }

  function selectItem() {
    $('.boxed').click(function(event){
      $('#message').val("");

      $('#itemNumDisplay').val("");

      var id = $(this).find('.number').text();

      var item = $('#itemNumDisplay');
      item.val(item.val() + id);
  });
}


  function getAllVendingItems() {

    var numList = document.querySelectorAll('.number');
    var numArray = Array.from(numList);
    for (i = 0; i < numArray.length; i ++) {
      numArray[i].innerText = "";
    }

    var nameList = document.querySelectorAll('.vendingName');
    var nameArray = Array.from(nameList);
    for (i = 0; i < nameArray.length; i ++) {
      nameArray[i].innerText = "";
    }

    var priceList = document.querySelectorAll('.price');
    var priceArray = Array.from(priceList);
    for (i = 0; i < priceArray.length; i ++) {
      priceArray[i].innerText = "";
    }

    var quantityList = document.querySelectorAll('.quantity');
    var quantityArray = Array.from(quantityList);
    for (i = 0; i < quantityArray.length; i ++) {
      quantityArray[i].innerText = "";
    }

      $("#12Box").hide();
      $('#11Box').hide();


      $.ajax ({
        type: 'GET',
        url: 'http://tsg-vending.herokuapp.com/items',
        success: function (items) {
          var idArray = $('.number');

          for (i = 0; i < items.length; i++){
            var item = items[i];
            idArray[i].append(item.id);
          }

          var nameArray = $('.vendingName');

          for (i = 0; i < items.length; i++){
            nameArray[i].append(items[i].name);
          }

          var priceArray = $('.price');

          for (i = 0; i < items.length; i++){
            priceArray[i].append('$' + items[i].price);
          }

          var quantityArray = $('.quantity');

          for (i = 0; i < items.length; i++){
            quantityArray[i].append('Quantity Left: ' + items[i].quantity);
          }
          },
        error: function(xhr) {
          var err = xhr.status + ': ' + xhr.responseJSON.message;
          $('#message').val(err);

          }
      });
  }

  function makePurchase() {
    $('#makePurchase').click(function(event){
      if ($('#itemNumDisplay').val() == "") {
        $('#message').val("Please choose an item!!");
        return;
      }
      $('#returnChange').hide();

      var id = $('#itemNumDisplay').val();
      var money = $('#add-money').val();

      $.ajax({
        type: 'POST',
        url: 'http://tsg-vending.herokuapp.com/money/' + money + '/item/' + id,
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        'dataType': 'json',
        success: function(coins) {
          var changeDisplay = $('#changeDisplay');
          var quar = coins.quarters;
          var dime = coins.dimes;
          var nickel = coins.nickels;
          var penny = coins.pennies;
          changeDisplay.val("");

          if (quar != 0) {
            changeDisplay.val(changeDisplay.val() + 'Quarters: ' + quar);
          }

          if (dime != 0) {
            changeDisplay.val(changeDisplay.val() + ', Dimes: ' + dime);
          }

          if (nickel != 0) {
            changeDisplay.val(changeDisplay.val() + ', Nickels: ' + nickel);
          }

          if (penny != 0) {
            changeDisplay.val(changeDisplay.val() + ', Pennies: ' + penny);
          }

          $('#message').val("Thank You!!");

          $('#add-money').val("0.00");

          getAllVendingItems();

        },
        error: function(xhr) {
          var err = xhr.status + ': ' + xhr.responseJSON.message;
          $('#message').val(err);
        }

      })
    });
  }


  function clickChange() {
    $('#returnChange').click(function(event){
      $('#message').val("");
      var changeDisplay = $('#changeDisplay');
      changeDisplay.val("");
      var currentMoney = parseFloat($('#add-money').val());

      var quar = 0;
      var dime = 0;
      var nickel = 0;
      var penny = 0;

      while (currentMoney > 0) {
        if ((currentMoney - 0.25) >= 0) {
          quar++;
          currentMoney = (currentMoney.toFixed(2) - 0.25);
        } else if ((currentMoney - 0.10) >= 0) {
          dime++;
          currentMoney = (currentMoney.toFixed(2) - 0.10);
        } else if ((currentMoney - 0.05) >= 0) {
          nickel++;
          currentMoney = (currentMoney.toFixed(2) - 0.05);
        } else if ((currentMoney - 0.01) >= 0) {
          penny++;
          currentMoney = (currentMoney.toFixed(2) - 0.01);
        }
      }


      if (quar != 0) {
        changeDisplay.val(changeDisplay.val() + 'Quarters: ' + quar);
      }

      if (dime != 0) {
        changeDisplay.val(changeDisplay.val() + ', Dimes: ' + dime);
      }

      if (nickel != 0) {
        changeDisplay.val(changeDisplay.val() + ', Nickels: ' + nickel);
      }

      if (penny != 0) {
        changeDisplay.val(changeDisplay.val() + ', Pennies: ' + penny);
      }

      $('#add-money').val("0.00");


    });
  }

/*
  function clearAndLoadInventory() {

    getAllVendingItems();


  }

  */
