<%-- 
    Document   : movieSeatSelection
    Created on : Dec 2, 2024, 11:57:04 AM
    Author     : arvin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seat Selection</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
    <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
    <link rel="stylesheet" href="assets/css/style.css"/>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="booking-details-top">
        <div class="booking-details-sub-top movie-title">
            <h1>Jurassic World:Dominion</h1>
            <div class="timer" id="timer">05:00</div>
        </div>
        <div class="booking-details-sub-top movie-locations">
            <h5><i class='fas fa-map-marker-alt'>&nbsp;</i>SilverKnight Cinemas - Colombo City Centre</h5>
        </div>
        <div class="booking-details-sub-top show-time">
            <h5>Show Time :</h5>
        </div>
    </div>
    <div class="seat-container-width">
        <div class="screen">Screen</div>
        <h2>Standard</h2>
        <hr>
        <div class="seat-booking-container">
            <div class="seat-container">
                <div class="seat-row">
                    <div class="seat available" data-seat-id="A1">A1</div>
                    <div class="seat available" data-seat-id="A2">A2</div>
                    <div class="seat available" data-seat-id="A3">A3</div>
                    <div class="seat available" data-seat-id="A4">A4</div>
                    <div class="seat available" data-seat-id="A5">A5</div>
                    <div class="seat available" data-seat-id="A6">A6</div>
                </div>
                <div class="seat-row">
                    <div class="seat available" data-seat-id="A7">A7</div>
                    <div class="seat available" data-seat-id="A8">A8</div>
                    <div class="seat available" data-seat-id="A9">A9</div>
                    <div class="seat available" data-seat-id="A10">A10</div>
                    <div class="seat available" data-seat-id="A11">A11</div>
                    <div class="seat available" data-seat-id="A12">A12</div>
                    <div class="seat available" data-seat-id="A13">A13</div>
                    <div class="seat available" data-seat-id="A14">A14</div>
                    <div class="seat available" data-seat-id="A15">A15</div>
                    <div class="seat available" data-seat-id="A16">A16</div>
                </div>
                <div class="seat-row">
                    <div class="seat available" data-seat-id="A17">A17</div>
                    <div class="seat available" data-seat-id="A18">A18</div>
                    <div class="seat available" data-seat-id="A19">A19</div>
                    <div class="seat available" data-seat-id="A20">A20</div>
                    <div class="seat available" data-seat-id="A21">A21</div>
                    <div class="seat available" data-seat-id="A22">A22</div>
                </div>
            </div>

            <div class="seat-container">
                <div class="seat-row">
                    <div class="seat available" data-seat-id="B1">B1</div>
                    <div class="seat available" data-seat-id="B2">B2</div>
                    <div class="seat available" data-seat-id="B3">B3</div>
                    <div class="seat available" data-seat-id="B4">B4</div>
                    <div class="seat available" data-seat-id="B5">B5</div>
                    <div class="seat available" data-seat-id="B6">B6</div>
                </div>
                <div class="seat-row">
                    <div class="seat available" data-seat-id="B7">B7</div>
                    <div class="seat available" data-seat-id="B8">B8</div>
                    <div class="seat available" data-seat-id="B9">B9</div>
                    <div class="seat available" data-seat-id="B10">B10</div>
                    <div class="seat available" data-seat-id="B11">B11</div>
                    <div class="seat available" data-seat-id="B12">B12</div>
                    <div class="seat available" data-seat-id="B13">B13</div>
                    <div class="seat available" data-seat-id="B14">B14</div>
                    <div class="seat available" data-seat-id="B15">B15</div>
                    <div class="seat available" data-seat-id="B16">B16</div>
                </div>
                <div class="seat-row">
                    <div class="seat available" data-seat-id="B17">B17</div>
                    <div class="seat available" data-seat-id="B18">B18</div>
                    <div class="seat available" data-seat-id="B19">B19</div>
                    <div class="seat available" data-seat-id="B20">B20</div>
                    <div class="seat available" data-seat-id="B21">B21</div>
                    <div class="seat available" data-seat-id="B22">B22</div>
                </div>
            </div>

            <div class="seat-container">
                <div class="seat-row">
                    <div class="seat available" data-seat-id="C1">C1</div>
                    <div class="seat available" data-seat-id="C2">C2</div>
                    <div class="seat available" data-seat-id="C3">C3</div>
                    <div class="seat available" data-seat-id="C4">C4</div>
                    <div class="seat available" data-seat-id="C5">C5</div>
                    <div class="seat available" data-seat-id="C6">C6</div>
                </div>
                <div class="seat-row">
                    <div class="seat available" data-seat-id="C7">C7</div>
                    <div class="seat available" data-seat-id="C8">C8</div>
                    <div class="seat available" data-seat-id="C9">C9</div>
                    <div class="seat available" data-seat-id="C10">C10</div>
                    <div class="seat available" data-seat-id="C11">C11</div>
                    <div class="seat available" data-seat-id="C12">C12</div>
                    <div class="seat available" data-seat-id="C13">C13</div>
                    <div class="seat available" data-seat-id="C14">C14</div>
                    <div class="seat available" data-seat-id="C15">C15</div>
                    <div class="seat available" data-seat-id="C16">C16</div>
                </div>
                <div class="seat-row">
                    <div class="seat available" data-seat-id="C17">C17</div>
                    <div class="seat available" data-seat-id="C18">C18</div>
                    <div class="seat available" data-seat-id="C19">C19</div>
                    <div class="seat available" data-seat-id="C20">C20</div>
                    <div class="seat available" data-seat-id="C21">C21</div>
                    <div class="seat available" data-seat-id="C22">C22</div>
                </div>
            </div>
            
            <div class="seat-container">
                <div class="seat-row">
                    <div class="seat available" data-seat-id="D1">D1</div>
                    <div class="seat available" data-seat-id="D2">D2</div>
                    <div class="seat available" data-seat-id="D3">D3</div>
                    <div class="seat available" data-seat-id="D4">D4</div>
                    <div class="seat available" data-seat-id="D5">D5</div>
                    <div class="seat available" data-seat-id="D6">D6</div>
                </div>
                <div class="seat-row">
                    <div class="seat available" data-seat-id="D7">D7</div>
                    <div class="seat available" data-seat-id="D8">D8</div>
                    <div class="seat available" data-seat-id="D9">D9</div>
                    <div class="seat available" data-seat-id="D10">D10</div>
                    <div class="seat available" data-seat-id="D11">D11</div>
                    <div class="seat available" data-seat-id="D12">D12</div>
                    <div class="seat available" data-seat-id="D13">D13</div>
                    <div class="seat available" data-seat-id="D14">D14</div>
                    <div class="seat available" data-seat-id="D15">D15</div>
                    <div class="seat available" data-seat-id="D16">D16</div>
                </div>
                <div class="seat-row">
                    <div class="seat available" data-seat-id="D17">D17</div>
                    <div class="seat available" data-seat-id="D18">D18</div>
                    <div class="seat available" data-seat-id="D19">D19</div>
                    <div class="seat available" data-seat-id="D20">D20</div>
                    <div class="seat available" data-seat-id="D21">D21</div>
                    <div class="seat available" data-seat-id="D22">D22</div>
                </div>
            </div>

            <div class="seat-container">
                <div class="seat-row">
                    <div class="seat available" data-seat-id="E1">E1</div>
                    <div class="seat available" data-seat-id="E2">E2</div>
                    <div class="seat available" data-seat-id="E3">E3</div>
                    <div class="seat available" data-seat-id="E4">E4</div>
                    <div class="seat available" data-seat-id="E5">E5</div>
                    <div class="seat available" data-seat-id="E6">E6</div>
                </div>
                <div class="seat-row">
                    <div class="seat available" data-seat-id="E7">E7</div>
                    <div class="seat available" data-seat-id="E8">E8</div>
                    <div class="seat available" data-seat-id="E9">E9</div>
                    <div class="seat available" data-seat-id="E10">E10</div>
                    <div class="seat available" data-seat-id="E11">E11</div>
                    <div class="seat available" data-seat-id="E12">E12</div>
                    <div class="seat available" data-seat-id="E13">E13</div>
                    <div class="seat available" data-seat-id="E14">E14</div>
                    <div class="seat available" data-seat-id="E15">E15</div>
                    <div class="seat available" data-seat-id="E16">E16</div>
                </div>
                <div class="seat-row">
                    <div class="seat available" data-seat-id="E17">E17</div>
                    <div class="seat available" data-seat-id="E18">E18</div>
                    <div class="seat available" data-seat-id="E19">E19</div>
                    <div class="seat available" data-seat-id="E20">E20</div>
                    <div class="seat available" data-seat-id="E21">E21</div>
                    <div class="seat available" data-seat-id="E22">E22</div>
                </div>
            </div>
            
            <div class="seat-container">
                <div class="seat-row">
                    <div class="seat available" data-seat-id="F1">F1</div>
                    <div class="seat available" data-seat-id="F2">F2</div>
                    <div class="seat available" data-seat-id="F3">F3</div>
                    <div class="seat available" data-seat-id="F4">F4</div>
                    <div class="seat available" data-seat-id="F5">F5</div>
                    <div class="seat available" data-seat-id="F6">F6</div>
                </div>
                <div class="seat-row">
                    <div class="seat available" data-seat-id="F7">F7</div>
                    <div class="seat available" data-seat-id="F8">F8</div>
                    <div class="seat available" data-seat-id="F9">F9</div>
                    <div class="seat available" data-seat-id="F10">F10</div>
                    <div class="seat available" data-seat-id="F11">F11</div>
                    <div class="seat available" data-seat-id="F12">F12</div>
                    <div class="seat available" data-seat-id="F13">F13</div>
                    <div class="seat available" data-seat-id="F14">F14</div>
                    <div class="seat available" data-seat-id="F15">F15</div>
                    <div class="seat available" data-seat-id="F16">F16</div>
                </div>
                <div class="seat-row">
                    <div class="seat available" data-seat-id="F17">F17</div>
                    <div class="seat available" data-seat-id="F18">F18</div>
                    <div class="seat available" data-seat-id="F19">F19</div>
                    <div class="seat available" data-seat-id="F20">F20</div>
                    <div class="seat available" data-seat-id="F21">F21</div>
                    <div class="seat available" data-seat-id="F22">F22</div>
                </div>
            </div>
            
            <div class="seat-container">
                <div class="seat-row">
                    <div class="seat available" data-seat-id="G1">G1</div>
                    <div class="seat available" data-seat-id="G2">G2</div>
                    <div class="seat available" data-seat-id="G3">G3</div>
                    <div class="seat available" data-seat-id="G4">G4</div>
                    <div class="seat available" data-seat-id="G5">G5</div>
                    <div class="seat available" data-seat-id="G6">G6</div>
                </div>
                <div class="seat-row">
                    <div class="seat available" data-seat-id="G7">G7</div>
                    <div class="seat available" data-seat-id="G8">G8</div>
                    <div class="seat available" data-seat-id="G9">G9</div>
                    <div class="seat available" data-seat-id="G10">G10</div>
                    <div class="seat available" data-seat-id="G11">G11</div>
                    <div class="seat available" data-seat-id="G12">G12</div>
                    <div class="seat available" data-seat-id="G13">G13</div>
                    <div class="seat available" data-seat-id="G14">G14</div>
                    <div class="seat available" data-seat-id="G15">G15</div>
                    <div class="seat available" data-seat-id="G16">G16</div>
                </div>
                <div class="seat-row">
                    <div class="seat available" data-seat-id="G17">G17</div>
                    <div class="seat available" data-seat-id="G18">G18</div>
                    <div class="seat available" data-seat-id="G19">G19</div>
                    <div class="seat available" data-seat-id="G20">G20</div>
                    <div class="seat available" data-seat-id="G21">G21</div>
                    <div class="seat available" data-seat-id="G22">G22</div>
                </div>
            </div>
            
        </div>
        <div class="ticket-selection">
            Adult Tickets: 
            <button class="btn-movie-selection" id="adult-decrement" disabled>-</button>
            <span id="adult-count">0</span>
            <button class="btn-movie-selection" id="adult-increment" disabled>+</button>
            <br>
            Child Tickets: 
            <button class="btn-movie-selection" id="child-decrement" disabled>-</button>
            <span id="child-count">0</span>
            <button class="btn-movie-selection" id="child-increment" disabled>+</button>
        </div>

        <div class="btn-movie-container">
            <button class="btn-navigate">Back</button>
            <button class="btn-navigate">Continue</button>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
    <script src="assets/scripts/main.js"></script>
</body>
</html>