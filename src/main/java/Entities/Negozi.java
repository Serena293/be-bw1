package Entities;

 public class Negozi extends Rivenditori {
     private String nomeNegozio;
     private String indirizzo;

     public Negozi(int bigliettiEmessi, int abbonamentiEmessi, String nomeNegozio, String indirizzo){
         super(bigliettiEmessi, abbonamentiEmessi);
         this.nomeNegozio = nomeNegozio;
         this.indirizzo = indirizzo;
     }

     public String getNomeNegozio() {
         return nomeNegozio;
     }

     public void setNomeNegozio(String nomeNegozio) {
         this.nomeNegozio = nomeNegozio;
     }

     public String getIndirizzo() {
         return indirizzo;
     }

     public void setIndirizzo(String indirizzo) {
         this.indirizzo = indirizzo;
     }

     @Override
     public String toString() {
         return "Negozi{" +
                 "nomeNegozio='" + nomeNegozio + '\'' +
                 ", indirizzo='" + indirizzo + '\'' +
                 '}';
     }
 }
