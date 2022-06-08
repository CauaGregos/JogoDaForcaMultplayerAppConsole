 public class ComunicadoPalavra extends Comunicado {

     String palavra;

     public ComunicadoPalavra(String palavra) {
         this.palavra = palavra;
     }

     public String toString() {
         return this.palavra;
     }

     @Override
     public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;
         ComunicadoPalavra c = (ComunicadoPalavra) o;
         return palavra == c.palavra;
     }

     @Override
     public int hashCode() {
         int ret = 666;
         ret = ret * 3 + palavra.hashCode();
         if(ret < 0)
             ret = -ret;
         return ret;

     }

     @Override
     public Object clone ()
     {
         // returnar uma copia de this
         ComunicadoPalavra ret = null;
         try
         { ret = new ComunicadoPalavra (this.palavra);

         }
         catch (Exception ignored) {
         }
         return ret;
     }

 }

