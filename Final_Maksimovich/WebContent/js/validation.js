function validateForm() {
    var dateField = document.getElementById("date-field");
    var optionalEmailField = document.getElementById("optional-email-field");
    var formSpanName = "form-err-span";
    var errorMessage = 'Not all fields filled correctly!';
    var messageColorName = 'red';

    if(validateDateField(dateField) && checkPassword() && optionalEmailField.value && checkOptionalEmailField(optionalEmailField)){
      return true;
      }
    else{
      setSpanText(formSpanName, errorMessage,messageColorName);
      return false;
    }
}

/*функция для проверки валидности заданного поля*/
function checkField(field) {
 if (field.checkValidity() == false) {
     field.style.backgroundColor = "rgba(255,0,0,0.3)";
     return false;
 } else {
     field.style.backgroundColor = "inherit";
     return true;
 }
}

/*функция для задания текста и цвета для него определенному span*/
function setSpanText(spanName, text, colorName){
  document.getElementById(spanName).innerText = text;
  document.getElementById(spanName).style.color = colorName;
}

function checkFirstNameField(firstNameField) {
  var firstNameSpanName = 'first-name-span';
  var errorMessage = '';
  var messageColor = 'inherit';

  if(!checkField(firstNameField)){
    errorMessage = firstNameField.title;
    messageColor = 'red';
  }
  setSpanText(firstNameSpanName, errorMessage, messageColor);
}

function checkLastNameField(lastNameField) {
  var lastNameSpanName = 'last-name-span';
  var errorMessage = '';
  var messageColor = 'inherit';

  if(!checkField(lastNameField)){
    errorMessage = lastNameField.title;
    messageColor = 'red';
  }
  setSpanText(lastNameSpanName, errorMessage, messageColor);
}

function checkEmailField(emailField) {
  var emailFieldSpanName = 'email-span';
  var errorMessage = '';
  var messageColor = 'inherit';

  if(!checkField(emailField)){
    errorMessage = 'Incorrect email format';
    messageColor = 'red';
  }
  setSpanText(emailFieldSpanName, errorMessage, messageColor);
}

function checkOptionalEmailField(optionalField) {
  var emailField = document.getElementById("email-field");
  var defaultBackGroundColorName = "inherit";
  var errorBackgroundColorName = "rgba(255,0,0,0.3)";
  var optionalEmailSpanName = 'optional-email-span';
  var errorMessage = '';
  var messageColor = 'inherit';

  if(checkField(optionalField)){
    if(optionalField.value !== emailField.value){
      optionalField.style.backgroundColor = defaultBackGroundColorName;
      setSpanText(optionalEmailSpanName, errorMessage, messageColor);
      return true;
    }else{
      errorMessage = 'Email and optional email can\'t be the same';
      messageColor = 'red';
    }
  }else{
    errorMessage = 'Incorrect optional email format';
    messageColor = 'red';
  }
    optionalField.style.backgroundColor = errorBackgroundColorName;
    setSpanText(optionalEmailSpanName, errorMessage, messageColor);
    return false;
}

function checkField(field) {
  var defaultBackGroundColorName = "inherit";
  var errorBackgroundColorName = "rgba(255,0,0,0.3)";

 if (field.checkValidity() == false) {
     field.style.backgroundColor = errorBackgroundColorName;
     return false;
 } else {
     field.style.backgroundColor = defaultBackGroundColorName;
     return true;
 }
}

function checkPasswordField(passwordField) {
  var backgroundColorName = "inherit";
  var passwordSpanName = 'password-span';
  var errorMessage = '';
  var messageColor = 'inherit';

   if (passwordField.checkValidity() == false) {
      errorMessage = passwordField.title;
      messageColor = 'red';
      backgroundColorName = "rgba(255,0,0,0.3)";
    }
      passwordField.style.backgroundColor = backgroundColorName;
      setSpanText(passwordSpanName, errorMessage, messageColor);
      resetRepPassword();
}

  /*функция сбрасывает значения связанные с полем повторного ввода пароля*/
function resetRepPassword(){
  var repPasswordField = document.getElementById("rep-password-field");
    repPasswordField.value = '';
    repPasswordField.style.backgroundColor = 'inherit';
    setSpanText("rep-password-span", '','inherit');
}

/*функция проверки обоих паролей*/
function checkPassword() {
  var passwordField = document.getElementById("password-field");
  var repPasswordField = document.getElementById("rep-password-field");
  var repPasswordSpanName = 'rep-password-span';
  var errorMessage = '';
  var messageColor = 'inherit';

  if( passwordField.value === repPasswordField.value ){
      passwordField.style.backgroundColor = 'inherit';
      repPasswordField.style.backgroundColor = 'inherit';
      setSpanText(repPasswordSpanName, errorMessage, messageColor);
      return true;
  }else{
      errorMessage = 'Passwords do not match';
      messageColor = 'red';
      passwordField.style.backgroundColor = 'rgba(255,0,0,0.3)';
      repPasswordField.style.backgroundColor = 'rgba(255,0,0,0.3)';
      setSpanText(repPasswordSpanName, errorMessage, messageColor);
      return false;
    }
}

function checkSecAnswerField(secAnswerField) {
  var secAnswerSpanName = 'secanswer-span';
  var errorMessage = '';
  var messageColor = 'inherit';

  if(!checkField(secAnswerField)){
    errorMessage = secAnswerField.title;
    messageColor = 'red';
  }
  setSpanText(secAnswerSpanName, errorMessage, messageColor);
}

function showOptionalBlock(addButton) {
  document.getElementById("optional").style.display= 'block';
  document.getElementById("optional-email-field").required = true;
  addButton.style.visibility='hidden';
}

function cancelOptionalBlock(cancelButton){
  document.getElementById("optional").style.display= 'none';
  document.getElementById("add-email-btn").style.visibility = 'visible';

/*сбрасываем значения опцыонального поля*/
  var optionalEmailField = document.getElementById("optional-email-field");
  optionalEmailField.value = '';
  optionalEmailField.style.backgroundColor = 'inherit';
  optionalEmailField.required = false;
}

/*валидным считается возраст [7,120)*/
function validateDateField(dateField){
  var LOWER_YEAR_BOUND = 7;
  var UPPER_YEAR_BOUND = 120;
  var date =  new Date(Date.parse(dateField.value));
  var now = new Date();
  var dateFullYear, dateMonth , dateDay = 0;
  var nowFullYear, nowMonth, nowDay = 0;
  var fullYearDifference = 0;
  var errorMessage = '';

  setSpanText("date-span", '','inherit');

  if(!isNaN(date)){
      dateFullYear = date.getFullYear();
      nowFullYear =  now.getFullYear();
      fullYearDifference = nowFullYear - dateFullYear;

      /*если разница попадает внутрь границ*/
      if(fullYearDifference > LOWER_YEAR_BOUND && fullYearDifference < UPPER_YEAR_BOUND){
        dateField.style.backgroundColor = 'inherit';
        return true;
      }

      dateMonth = date.getMonth();
      dateDay = date.getDate();

      nowMonth = now.getMonth();
      nowDay = now.getDate();
      /*если разница попадает на нижнюю границу*/
      if(fullYearDifference === LOWER_YEAR_BOUND ){

        if(nowMonth > dateMonth || (nowMonth === dateMonth && nowDay >= dateDay) ){
          dateField.style.backgroundColor = 'inherit';
          return true;
        }
      }
      /*если разница попадает на верхнюю границу*/
      if (fullYearDifference === UPPER_YEAR_BOUND){

        if(nowMonth < dateMonth || (nowMonth === dateMonth && nowDay < dateDay) ){
          dateField.style.backgroundColor = 'inherit';
          return true;
        }
      }
      errorMessage = 'Your age have to be between' + LOWER_YEAR_BOUND + ' and ' + UPPER_YEAR_BOUND;
    }else{
      errorMessage = dateField.title;
    }
    dateField.style.backgroundColor = 'rgba(255,0,0,0.3)';
    setSpanText("date-span", errorMessage,'red');

    return false;
}
