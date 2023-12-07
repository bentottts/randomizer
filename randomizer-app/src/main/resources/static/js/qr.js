function docReady(fn) {
    // see if DOM is already available
    if (document.readyState === "complete" || document.readyState === "interactive") {
        // call on next available tick
        setTimeout(fn, .5);
    } else {
        document.addEventListener("DOMContentLoaded", fn);
    }
}

var html5QrcodeScanner;

docReady(function() {
    initScanner();
});



function showSweetAlert(myCandidateObj) {
   Swal.fire({
       title: 'Hi ' + myCandidateObj.firstName + ' ' + myCandidateObj.lastName + '!',
       text: 'Thank you for registering.',
       icon: 'success',
       confirmButtonText: 'OK',
       allowOutsideClick: false
   }).then((result) =>{
   if (result.isConfirmed) {
        // do something on okay
        saveCandidate(myCandidateObj);
     }
   });
}

function initScanner(){
    var resultContainer = document.getElementById('qr-reader-results');
    var lastResult, countResults = 0;

    html5QrcodeScanner = new Html5QrcodeScanner(
        "qr-reader", { fps: 10, qrbox: 250 });

    function onScanSuccess(decodedText, decodedResult) {

        html5QrcodeScanner.pause();
        if (decodedText !== lastResult) {
            console.log(`Scan result = ${decodedText}`, decodedResult);
            lastResult = decodedText;
            // check if decoded text is null or empty
            if(decodedText === null || decodedText === undefined){
                throwError("The QR Code is invalid!")
                return;
            }

            // Initializing the object with dynamic fields using the Object constructor
            var myCandidateObj = new Object();

            // spilt the string ny comma
            var arrayCandidateProp = decodedText.split(",");

           try{
            for(var i of arrayCandidateProp){
                var arrayField = i.split(":");
                if(arrayField.length == 2){
                    var fieldName = arrayField[0];
                    var fieldValue = arrayField[1];
                    myCandidateObj[fieldName] = fieldValue;
                }
                console.log(myCandidateObj)
            }
            showSweetAlert(myCandidateObj);
           }catch(error){
                throwError("Invalid QR Code, try generating a new one.");
           }
        }else{
            throwWaring("Duplicate request!");
            setTimeout(() => {
              // Resume the scanner after the pause
              html5QrcodeScanner.resume();
            }, 2000);
        }
    }

    // Optional callback for error, can be ignored.
    function onScanError(qrCodeError) {
        // This callback would be called in case of qr code scan error or setup error.
        // You can avoid this callback completely, as it can be very verbose in nature.
    }

    html5QrcodeScanner.render(onScanSuccess, onScanError);
}

function saveCandidate(myCandidateObj){

    fetch('/randomizer-app/v1/api/save-candidate', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            // Add other headers if needed
        },
        body: JSON.stringify(myCandidateObj)
    })
    .then(response => response.json())
    .then(responseObj => {
        console.log('Saving Response:', responseObj);
        if(responseObj.hasOwnProperty('message')){
            Swal.fire({
              icon: "warning",
              title: responseObj.message,
              showConfirmButton: false,
              timer: 1500
            });
        }else{
            Swal.fire({
              icon: "success",
              title: "Registration done!",
              showConfirmButton: false,
              timer: 1500
            });

        }
    })
    .catch((error) => {
        console.error('Error:', error);
        // Handle errors
        Swal.fire({
          icon: "warning",
          title: "Something went wrong!",
          showConfirmButton: false,
          timer: 1500
        });
    });

    setTimeout(() => {
      // Resume the scanner after the pause
      html5QrcodeScanner.resume();
    }, 2000);
}


function throwError(message){
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: "Something went wrong: "+message
    });
}

function throwWaring(message){
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: message,
      showConfirmButton: false,
      timer: 1500
    });
}