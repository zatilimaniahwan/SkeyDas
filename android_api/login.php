<?php


require_once 'include/DB_Functions.php';
$db = new DB_Functions();

// json response array
$response = array("error" => FALSE);

if (isset($_POST['email']) && isset($_POST['password'])) {

    // receiving the post params
    $email = $_POST['email'];
    $password = $_POST['password'];

    // get the user by email and password
    $device = $db->getDeviceByEmailAndPassword($email, $password);

    if ($device != false) {
        // use is found
        $response["error"] = FALSE;
        $response["uid"] = $device["unique_id"];
        $response["device"]["device_name"] = $device["device_name"];
        $response["device"]["email"] = $device["email"];
        $response["device"]["created_at"] = $device["created_at"];
        $response["device"]["updated_at"] = $device["updated_at"];
        echo json_encode($response);
    } else {
        // user is not found with the credentials
        $response["error"] = TRUE;
        $response["error_msg"] = "Login credentials are wrong. Please try again!";
        echo json_encode($response);
    }
} else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters email or password is missing!";
    echo json_encode($response);
}
?>



