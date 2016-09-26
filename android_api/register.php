<?php



require_once 'include/DB_Functions.php';
$db = new DB_Functions();

// json response array
$response = array("error" => FALSE);

if (isset($_POST['device_name']) && isset($_POST['email']) && isset($_POST['password'])) {

    // receiving the post params
    $name = $_POST['device_name'];
    $email = $_POST['email'];
    $password = $_POST['password'];

    // check if user is already existed with the same email
    if ($db->isDeviceExisted($email)) {
        // user already existed
        $response["error"] = TRUE;
        $response["error_msg"] = "Device already existed with " . $email;
        echo json_encode($response);
    } else {
        // create a new user
        $device = $db->storeUser($device_name, $email, $password);
        if ($device) {
            // user stored successfully
            $response["error"] = FALSE;
            $response["uid"] = $device["unique_id"];
            $response["device"]["name"] = $device["name"];
            $response["device"]["email"] = $device["email"];
            $response["device"]["created_at"] = $device["created_at"];
            $response["device"]["updated_at"] = $device["updated_at"];
            echo json_encode($response);
        } else {
            // user failed to store
            $response["error"] = TRUE;
            $response["error_msg"] = "Unknown error occurred in registration!";
            echo json_encode($response);
        }
    }
} else {
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters (name, email or password) is missing!";
    echo json_encode($response);
}
?>

