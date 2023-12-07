$("document").ready(function() {
  rollClick();
  clearForm();
});

//const entrants = ['Adam', 'Bob', 'Chris', 'Dave', 'Evan', 'Fred', 'Guy', 'Hussai'];

const entrants = ["Toppings", "Al's","Tes","El","Em","Al","Ven","Son","Val","kev","Jek","Mar","Gina","Loy","Adam", "Bob", "Chris", "Dave", "Evan", "Fred", "Guy", "Hussai","Toppings", "Al's","Tes","El","Em","Al","Ven","Son","Val","kev","Jek","Mar","Gina","Loy","Adam", "Bob", "Chris", "Dave", "Evan", "Fred", "Guy", "Hussai","Toppings", "Al's","Tes","El","Em","Al","Ven","Son","Val","kev","Jek","Mar","Gina","Loy","Adam", "Bob", "Chris", "Dave", "Evan", "Fred", "Guy", "Hussai","Toppings", "Al's","Tes","El","Em","Al","Ven","Son","Val","kev","Jek","Mar","Gina","Loy","Adam", "Bob", "Chris", "Dave", "Evan", "Fred", "Guy", "Hussai"];

function randomName() {
  var rand = Math.floor(Math.random() * entrants.length);
  var name = entrants[rand];
  $("#names").text(name);
}

function rollClick() {
  $("#roll").on("click", function(e) {
    $(this).hide();
    $("#clear").hide();
    $("#names").show();

    setDeceleratingTimeout(
      function() {
        randomName();
      },
      10,
      40
    );

    setTimeout(function() {
      var winner = $("#names").text();
      $("#winner").text(winner);
      $("#names").hide();
      $("#winner").html("<span>Congratulations!!!<br></span>" + winner);
//      $("#roll").show();
      $("#clear").show();
    }, 4000);

    e.preventDefault();
  });
}

function clearForm(){
  $("#clear").on("click", function(e) {
    $(this).hide();
        $("#names").hide();
        $("#winner").text("");
        $("#roll").show();
    });
}

function setDeceleratingTimeout(callback, factor, times) {
  var internalCallback = (function(t, counter) {
    return function() {
      if (--t > 0) {
        window.setTimeout(internalCallback, ++counter * factor);
        callback();
      }
    };
  })(times, 0);

  window.setTimeout(internalCallback, factor);
}
