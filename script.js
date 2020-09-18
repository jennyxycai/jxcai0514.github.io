var playerTurn = "X";
var winner = "";
var start = false;
var cells = 0;

function play(id){
  start = true;

  cells++;
  if (playerTurn == "X")
      playerTurn = "O";
  else
    playerTurn = "X";
  document.getElementById(id).innerHTML = playerTurn;
  document.getElementById("pStat").innerHTML = checkStatus();
  
    return playerTurn;
}
function checkStatus(){
  if (!start)
    return "Idle";
  else if (checkWin())
    return winner + " won!";
  else if (cells == 9){
    return "Cats game!";
    }
  else
    return "Playing"
}

function checkWin(){
  if (document.getElementById("1").innerHTML == document.getElementById("2").innerHTML && document.getElementById("2").innerHTML==document.getElementById("3").innerHTML || document.getElementById("1").innerHTML == document.getElementById("4").innerHTML && document.getElementById("4").innerHTML==document.getElementById("7").innerHTML || document.getElementById("1").innerHTML == document.getElementById("5").innerHTML && document.getElementById("5").innerHTML==document.getElementById("9").innerHTML){
    winner = document.getElementById("1").innerHTML;
    return true;
  }
    
  else if (document.getElementById("4").innerHTML == document.getElementById("5").innerHTML && document.getElementById("5").innerHTML==document.getElementById("6").innerHTML ||  document.getElementById("2").innerHTML== document.getElementById("5").innerHTML && document.getElementById("5").innerHTML==document.getElementById("8").innerHTML || document.getElementById("3").innerHTML == document.getElementById("5").innerHTML &&document.getElementById("5").innerHTML==document.getElementById("7").innerHTML) {

    winner = document.getElementById("5").innerHTML;
    return true;
  }

  else if (document.getElementById("7").innerHTML == document.getElementById("9").innerHTML && document.getElementById("8").innerHTML==document.getElementById("9").innerHTML ||document.getElementById("3").innerHTML == document.getElementById("9").innerHTML && document.getElementById("6").innerHTML==document.getElementById("9").innerHTML ){ 

    winner = document.getElementById("9").innerHTML;
    return true;
  }

  else{
    return false;
  }
}