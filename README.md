# Accounts-And-Games
this project have 2 classes 
the flieUtiles - this one has is responsible for files 
exists - checkds if a file exits in your computer. for example this checks in the file Accounts exists in C:
create a main class and add this line System.out.println("exists?" + fileUtiles.exists("C:\\Accounts"));  

create - creating a file in your computer this file will store usernames and passwords.
create a main class and add this line System.out.println("created?" + fileUtiles.create("C:\\Accounts", true));
it will create a file in C: and the name of the file will be Accounts

delete - this one deleting a file from your computer 
create a main class and add this line System.out.println("deleted?" + fileUtiles.delete("C:\\Accounts"));
it will delete the file Accounts from the C:

writeFile - this one can write inside a note file (it will automaticly create the note file with the given name)
 fileUtiles.writeFile("C:\\Accounts\\access", password, username); - this will create a access note and will write what you want (in this case password and username)
 (if the Accounts folder does not exits it will create it in C: and in it it will create the note)

 found - this one checking if a given word exixt in the note folder 
 fileUtiles.found(username, password, "C:\\Accounts\\access") (in this case it will check if a password or a user name exist in the folder)

 the second class (Main)
 will run the program it only uses writeFile and found methods it checkds if you have an account if yes it will ask for the username and password and if not it will ask you to log in
 them it will store the username and password in the note folder ("C:\\Accounts\\access") and next time you will run the program just log in with the same password and username
 after it the program will "start" and you will be able to play games

 there are 2 games 
 1. the last match (againts the computer) - explain in a different project 
 2. guess the word (2 players) - one enter a word and the second player enter letter until he guess the word 
