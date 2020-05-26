import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class TemperaturaFactory{
   ConexaoBD ct =null;
   Connection conn=null;
   ResultSet resp = null;
   PreparedStatement ps=null;
   double media;
   double menor;
   double maior;
   
   
   String stmt= "SELECT * FROM TEMPERATURA LIMIT ?";
   
   
   
   public String[][] completaTabela(int n, double media, double maior, double menor){
      //String quantidade= ""+n;
      menor=9999999;
      maior=0;
      media=0;
      String[][] tabela;
      tabela=new String[n][1];
      try{
         ct =new ConexaoBD();
         conn=ct.conectar();
         ps=conn.prepareStatement(stmt);
         ps.setInt(1, n);
         resp= ps.executeQuery();
         int i=0;
         while(resp.next()){
            if(i<n){
               tabela[i][0]= resp.getString("valor");
               double comparacao= Double.parseDouble(resp.getString("valor"));
               if(menor>comparacao){
                   menor=comparacao;   
               }else if(maior<comparacao){
                   maior=comparacao;
               }
               media=media+comparacao;  
               i++; 
            }   
         }
         ps.close();
         resp.close();
         this.media=media/n;
         this.maior=maior;
         this.menor=menor;
         return tabela;
      }catch(SQLException e){
         e.printStackTrace();
         return tabela;
      }finally{
         try{
            
            if (conn != null){
               conn.close(); 
            }
            
         }catch(SQLException t){
            t.printStackTrace();
         }
      }
   }
   
   public double getMedia(){
      return media;
   }
   public double getMenor(){
      return menor;
   }
   public double getMaior(){
      return maior;
   }
   
   
}