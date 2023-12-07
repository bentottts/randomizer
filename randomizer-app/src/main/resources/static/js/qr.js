function docReady(fn) {
    // see if DOM is already available
    if (document.readyState === "complete" || document.readyState === "interactive") {
        // call on next available tick
        setTimeout(fn, .5);
    } else {
        document.addEventListener("DOMContentLoaded", fn);
    }
}

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

    var html5QrcodeScanner = new Html5QrcodeScanner(
        "qr-reader", { fps: 10, qrbox: 250 });

    function onScanSuccess(decodedText, decodedResult) {
        if (decodedText !== lastResult) {
            ++countResults;
            lastResult = decodedText;
            console.log(`Scan result = ${decodedText}`, decodedResult);

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
                    const fieldName = arrayField[0];
                    myCandidateObj[fieldName] = arrayField[1];
                }
                console.log(myCandidateObj)
            }
           }catch(error){
                throwError("Invalid QR Code, try generating a new one.");
           }
            showSweetAlert(myCandidateObj);

            // Optional: To close the QR code scannign after the result is found
                  //html5QrcodeScanner.clear();
                  //html5QrcodeScanner.stop();
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
    .then(myCandidateObj => {
        console.log('Success:', myCandidateObj);
        Swal.fire({
          icon: "success",
          title: "Registration done!",
          showConfirmButton: false,
          timer: 1500
        });
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

}


function throwError(message){
Swal.fire({
  icon: "error",
  title: "Oops...",
  text: "Something went wrong: "+message
});
}
