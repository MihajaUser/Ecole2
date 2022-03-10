<!DOCTYPE html>
<html lang="en">
<head>
  <title>Professeur</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="style.css">
  <script src="js/jquery.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <style>

     header {
      height: 130px;
      margin-bottom: 10px;
      border-radius: 10px;
      background-color: white;
      margin-right: 10px;
      margin-left: 10px;
      
    }

    footer {
      background-color: #f2f2f2;
      padding: 25px;
    }

     header h1{
      position: relative;
        color: black;
        font-size: 40px;
        margin-bottom: 0px;
        margin-top: 30px;
          
     }

    .panel{
      margin-left: 50px;
    }

    /*.Antegnany{
      margin-right: 5%;
      margin-left: 50%; 
    }*/

    .sidebar{
      margin-right: 83%;
      top: 0;
      left: 0;
      height: 1000%;
      border-radius: 10px;
      background-color: white;
      width: 150px;
      padding:  14px;
    }


    .row{
      flex-shrink: 0;
      width: 100%;
      max-width: 100%;
      padding-right: calc(var(--bs-gutter-x) * .5);
      padding-left: calc(var(--bs-gutter-x) * .5);
      margin-top: var(--bs-gutter-y):
      --bs-gutter-x: 1.5rem:
      --bs-gutter-y: 0;
     /* margin-right: 83%;
      margin-left: 1%;*/
    }



    .col-md-2 {
      flex: 0 0 auto;
      width: 15%;
    }


     body{
      border-radius: 10px;
      height: 500px;
      margin-top: 30px;
      margin-bottom: 20px;
      margin-right: 0px;
      margin-left: 0px;
      background-color: gray;
      width: 100%;
    
     }

     footer {
        height: 10px;
        border-radius: 10px;
        margin-bottom: 10px;
        margin-top: 15px;
        margin-right: 2%;
        margin-left: 2%;
     }
  </style>
</head>
<body>
    <div class="zakabe">
        <header>
      <div class="col-sm-4">      
          <ul class="container text-center">
          <h1>
            <ul class="nav navbar-nav navbar-left">
              <li><span class="glyphicon glyphicon-education"></span></li>
            </ul>Ecole
          </h1>
        </ul>
      </div>
    </header>

    <div class="container">    
        <div class="row">
            <div class="col-md-2">
                <div class="sidebar">
              <ul class="nav_list">
                <li class="active"><a href="Ecolage.jsp">Ecolage</a></li>
                <li><a href="note.jsp">Note</a></li>
                <li><a href="classe.jsp">Classe</a></li>
              </ul>
            </div>
            </div>

              <div class="col-md-10">
              <nav class="navbar navbar-inverse">
                <div class="containerBar">
                  <div class="navbar-header">
                    <span class="glyphicon glyphicon-education"></span>
                  </div>
                  <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                      <li class="active"><a href="">Acceuil</a></li>
                      <li><a href="prof.jsp">Professeur</a></li>
                      <li><a href="elevenote.js">Etudiant</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                      <li><a href="#"><span class="glyphicon glyphicon-menu-hamburger"></span></a></li>
                    </ul>
                  </div>
                </div>
              </nav>
            <div class="panel panel-danger">
              <div class="panel-heading"></div>
              <div class="panel-body">
                  <form method="post">
                    <h1>Inscription Note</h1>
                    <p>Matricule <input type="text" name="matricule"></p>
                    <p>Nom <input type="text" name="name"></p>
                    <p><input type="checkbox" name="v">Payer</p>
                    <p><input type="submit" placeholder="entrer"></p>
                  </form>
              </div>
              <div class="panel-footer"></div>
          </div>       
        </div>
        </div>
            
          
    </div>

    <footer class="container-fluid text-center">
      <p></p>  
    </footer>
    </div>  
</body>

<!-- Mirrored from www.w3schools.com/bootstrap/tryit.asp?filename=trybs_temp_store&stacked=h by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 27 Jan 2020 03:08:48 GMT -->
</html>
