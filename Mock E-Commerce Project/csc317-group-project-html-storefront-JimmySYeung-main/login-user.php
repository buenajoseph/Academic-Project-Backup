<?php require_once "controllerUserData.php"; ?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login Form</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="storestyle.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
     <table background="background/alligator1.jpg" width="70%" height="100" align="center">

        <tr>

            <label for="category"></label>
            <th scope="col">
                <div class="dropdown">
                    <button class="dropbtn">Theme store</button>
                    <div class="dropdown-content">
                        <button type="button" onclick="bgchange1()">Spiral Book
                            Theme</button>
                        <br>
                        <button type="button" onclick="bgchange2()">Alligator Theme</button>
                        <br>
                        <button type="button" onclick="bgchange3()">Water Theme</button>
                        <br>
                        <button type="button" onclick="bgchange4()">Sand footprint Theme</button>
                    </div>
                </div>
            </th>
            <th scope="col">
                <div class="dropdown">
                    <img src="woodbar/categories1.png">
                    <div class="dropdown-content">
                        <a href="index.html">All</a>
                        <a href="textbooks.html">Textbooks</a>
                        <a href="manga.html">Manga</a>
                        <a href="toys.html">Anime Figurines</a>
                    </div>
                </div>
            </th>
            <div class="woodbar">

                <th scope="col"><a href="index.html"><img src="woodbar/home1.png" class="wood"></th>
                <div class="wrap">
                    <th scope="col">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1">
                                    <span class="fa fa-search" class="form-inline">
                                        <input autofocus placeholder="search" class="form-inline" type="text"
                                            autocomplete="off" name="search" id="search" />
                                    </span>
                                </span>
                            </div>

                            <!--This shows the list of search in website-->
                            <ul class="list-group" id="list"></ul>
                            <script src="search.js"></script>
                        </div>


                </div>

            </div>

            <div class="woodbar">
                <th scope="col"><a href="account.html"><img src="woodbar/account1.png" class="wood"></th>
                <th scope="col"><a href="products/empty.html"><img src="woodbar/cart1.png" class="wood"></th>
            </div>
            </div>

        </tr>
    </table> 
<br><br><br><br><br><br><br><br><br><br>
    <table class="container">
                <tr>
            <th width="25%"></th>
            <th width="10%"></th>
            <th width="15%"></th>
            <th width="15%"></th>
            <th width="10%"></th>
            <th width="25%"></th>
        </tr>
        <div class="row">
            <div class="col-md-4 offset-md-4 form login-form">
                <form action="login-user.php" method="POST" autocomplete="">
                    <h2 class="text-center">Login Form</h2>
                    <p class="text-center">Login with your email and password.</p>
                    <?php
                    if(count($errors) > 0){
                        ?>
                        <div class="alert alert-danger text-center">
                            <?php
                            foreach($errors as $showerror){
                                echo $showerror;
                            }
                            ?>
                        </div>
                        <?php
                    }
                    ?>
                    <div class="form-group">
                        <input class="form-control" type="email" name="email" placeholder="Email Address" required value="<?php echo $email ?>">
                    </div>
                    <div class="form-group">
                        <input class="form-control" type="password" name="password" placeholder="Password" required>
                    </div>
                    <div class="link forget-pass text-left"><a href="forgot-password.php">Forgot password?</a></div>
                    <div class="form-group">
                        <input class="form-control button" type="submit" name="login" value="Login">
                    </div>
                    <div class="link login-link text-center">Not yet a member? <a href="signup-user.php">Signup now</a></div>
                </form>
            </div>
        </div>
                </table>
        <div id="return-to-home">
        <br><br><br><br><br><br><br><br><br><br>
        <fieldset class="backgroundads" style="float:right"></fieldset>
        <fieldset class="backgroundads1"></fieldset>
        <br><br><br><br><br><br><br><br><br><br>
        <br><br><br><br><br><br><br><br><br><br>
        <table align="center">
            <tr>
                <th></th>
            </tr>
            <tr>
                <td>
                    <a href="index.html"><img src="woodbar/returnhomepage.jpg" class="wood">
                </td>
            </tr>
        </table>

    </div>
    <div class="woodalign">
        <a href="companyinfo.html"><img src="woodbar/companyinfo1.png" class="wood">
        <a href="faq.html"><img src="woodbar/FAQ1.png" class="wood">
    </div>
</body>
</html>