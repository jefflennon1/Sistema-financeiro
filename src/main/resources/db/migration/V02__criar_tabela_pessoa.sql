CREATE TABLE pessoa(
codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL,
ativo TINYINT(2) NOT NULL,
logradouro VARCHAR(50),
numero VARCHAR(9) , 
complemento VARCHAR(50), 
bairro VARCHAR(50), 
cep INT(9), 
cidade VARCHAR(50), 
estado VARCHAR(15)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO pessoa(nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado)
VALUES
("Cauãn",1,"rua santa inês","113",null,"Cristo redentor",60335020,"Fortaleza","Ceará"),
("Monalisa",0,"Travessa são luiz","220","Altos","Colônia",60335020,"Fortaleza","Ceará"),
("Monique",1,"rua santa elisa","1123","garangem","Pirambu",60335020,"Fortaleza","Ceará"),
("Marcos",1,"rua floriano peixoto","10",null,"Centro",60335020,"Fortaleza","Ceará"),
("Vidjck",1,"rua alberto barrano","1313"," ap 1215","aldeota",60335020,"Fortaleza","Ceará"),
("Manoel carlos",0,"rua Franciando branco","1515", "","Parangaba",60335020,"Fortaleza","Ceará"),
("Carlos alberto",1,"rua alberto barrano","1313"," ap 1215","aldeota",60335020,"Fortaleza","Ceará"),
("Jonas",1,"rua alberto barrano","1313"," ap 1215","aldeota",60335020,"Fortaleza","Ceará"),
("Yago lucas",1,"rua alberto barrano","1313"," ap 1215","aldeota",60335020,"Fortaleza","Ceará"),
("Thiago alberto",1,"rua alberto barrano","1313"," ap 1215","aldeota",60335020,"Fortaleza","Ceará"),
("Kelton",1,"rua alberto barrano","1313"," ap 1215","aldeota",60335020,"Fortaleza","Ceará"),
("Berilo",1,"rua alberto barrano","1313"," ap 1215","aldeota",60335020,"Fortaleza","Ceará"),
("Vãnnia",1,"rua alberto barrano","1313"," ap 1215","aldeota",60335020,"Fortaleza","Ceará"),
("Camila",1,"rua alberto barrano","1313"," ap 1215","aldeota",60335020,"Fortaleza","Ceará"),
("Vera",1,"rua alberto barrano","1313"," ap 1215","aldeota",60335020,"Fortaleza","Ceará"),
("Monique",1,"rua alberto barrano","1313"," ap 1215","aldeota",60335020,"Fortaleza","Ceará");

