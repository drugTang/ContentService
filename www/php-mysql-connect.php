<?php
	$servername = "localhost";
	$username = "root";
	$password = "jx19940119";
	
	//创建连接
	$conn = new mysqli($servername,$username,$password,"testdb");
	
	//检测连接
	if($conn -> connect_error) {
		die("Connection failed:".$conn->connect_error);
	}
	
	$sql = "Create DATABASE if not exists testDB";
	//创建数据库
	if($conn->query($sql) === TRUE) {
		echo "DATABASE CREATE SUCCESSFULLY!";
	} else {
		echo "ERROR creating database:".$conn->error;
	}
	
	$sql_create_table = "CREATE TABLE MyGuests (
		id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
		firstname VARCHAR(30) NOT NULL,
		lastname VARCHAR(30) NOT NULL,
		email VARCHAR(50),
		reg_date TIMESTAMP
	)";
	
	if($conn->query($sql_create_table) === TRUE) {
		echo "TABLE CREATE SUCCESSFULLY";
	} else {
		echo "ERROR creating table:".$conn->error;
	}
	
	$conn->close();
?>