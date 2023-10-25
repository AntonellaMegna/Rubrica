


function visibilita(idD){
    alert(idD)
        switch (idD) {
            case 'contatti':
                document.getElementById("tabella").style.display="block";
                document.getElementById("ins").style.display="none";
                document.getElementById("mod").style.display="none";
                 
                dataBinding("my_table")
                break;
                case'insert':
                document.getElementById("ins").style.display="block";
                document.getElementById("mod").style.display="none";
                
                document.getElementById("tabella").style.display="none";
                break;
                case 'modifica':
                    document.getElementById("tabella").style.display="none";
                    document.getElementById("ins").style.display="none";
                    document.getElementById("mod").style.display="block";
                    
                  
                break;
               
             
               
            default:
            	 
                break;
        }
        
        
    }

    function cancella(){

      const contatti = {
        contattiId:document.getElementById("Upid").value,
        first_name: document.getElementById("Upnome").value,
        last_name:  document.getElementById("Upcognome").value,
        email: document.getElementById("Upemail").value,
        cell1: document.getElementById("Upcell").value
        }
        
      const opzioni = {
      method: 'DELETE',
      body: JSON.stringify(contatti),
      headers: {
        'Content-Type': 'application/json'
      }
      }
    
      fetch('http://localhost:8080/Rubrica/rest/metodi/delC',opzioni)
      .then(res => res.json())
      .then(res => alert(res));
      }
        
function modifica(){

	const contatti = {
	  contattiId:document.getElementById("Upid").value,
      first_name: document.getElementById("Upnome").value,
	  last_name:  document.getElementById("Upcognome").value,
	  email: document.getElementById("Upemail").value,
	  cell1: document.getElementById("Upcell").value
	  }
	  
	const opzioni = {
	method: 'PUT',
	body: JSON.stringify(contatti),
	headers: {
	  'Content-Type': 'application/json'
	}
	}

	fetch('http://localhost:8080/Rubrica/rest/metodi/updateCont',opzioni)
	.then(res => res.json())
	.then(res => alert(res));
	}
	  


function selEmail(){

fetch('http://localhost:8080/Rubrica/rest/metodi/selC/'+ document.getElementById("selEm").value)
//.then(response=> response.json())
.then(response => {
 if (response.ok) {
    return response.json();
 }
 else{ console.log("errore")}
})
.then(data=>{
        document.getElementById("Upid").value=data.contattiId
        document.getElementById("Upnome").value=data.last_name
        document.getElementById("Upcognome").value=data.first_name
        document.getElementById("Upemail").value=data.email
        document.getElementById("Upcell").value=data.cell1
       
  })
}




 function inserisci(){

const contatti = {
  first_name: document.getElementById("nome").value,
  last_name:  document.getElementById("cognome").value,
  email: document.getElementById("email").value,
  cell1: document.getElementById("cell").value
  }
  
const opzioni = {
method: 'POST',
body: JSON.stringify(contatti),
headers: {
  'Content-Type': 'application/json'
}
}

fetch('http://localhost:8080/Rubrica/rest/metodi/insertCon', opzioni)
.then(res => res.json())
.then(res => alert(res));
}
  


  function dataBinding(id_table){
   fetch('http://localhost:8080/Rubrica/rest/metodi/selAll')
   //.then(response=> response.json())
   .then(response => {
    if (response.ok) {
       return response.json();
    }
    else{ console.log("errore")}
  })
   .then(data=>{
    document.getElementById("load").style.display="none";
    document.getElementById("tabella").style.display="block";
          for (const c of data) {
          aggiungiRiga(id_table,c.first_name,c.last_name,c.email, c.cell1);
         console.log(c.first_name,c.last_name,c.email, c.cell1)
      }
   })
   
  
}
  
  
  
  
  function aggiungiRiga(id_table,nome,cognome,email, cell){
    var table = document.getElementById(id_table);
  
    var tbody = table.getElementsByTagName('tbody')[0];
  
    var colonne = table.getElementsByTagName('th').length;
  
    var tr = document.createElement('tr');
  
    for(var i=0; i<colonne; i++){
      var td = document.createElement('td');
      switch(i){
         case 0:
         var tx = document.createTextNode(nome);
          break;
          case 1:
          var tx = document.createTextNode(cognome);
          break;
          case 2:
          var tx = document.createTextNode(email);
            break;
            case 3:
            var tx = document.createTextNode(cell);
            break;
            default:
            var tx = document.createTextNode('####');

      }
     
      td.appendChild(tx);
      tr.appendChild(td);
    }
   
    tbody.appendChild(tr);
  }

