<?

try{

header("Content-Type:application/json");

$votation_id = intval($_POST["votation_id"]);
$vote = $_POST["vote"];
$servername = "127.7.219.2";
$username = "adminlg4UZhi";
$password = "FvNTkqswPP5i";
$dbname = "php";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($votation_id === 0 || $conn->connect_error) {
    throw new Exception;
} 

$sql = "INSERT INTO Votes(vote, votation_id) VALUES ('$vote', '$votation_id')";
$result = $conn->query($sql);
echo json_encode(array("msg"=>"1"));
$conn->close();
}catch(Exception $e){
echo json_encode(array("msg"=>0));}
die();

?>

