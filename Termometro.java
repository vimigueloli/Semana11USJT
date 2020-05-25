import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Termometro {
	//o metodo precisa avisar que vai lancar uma Exception pois so trata SQLException
	public Temperatura[] ultimosDias(Connection conn, int numero) throws Exception{
		Temperatura[] temperaturas = new Temperatura[numero];
		String sqlSelect = "SELECT id, valor FROM TEMPERATURA ORDER BY id DESC";
		Temperatura temperatura;

		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery();
			int i = 0;
			while (rs.next() && i < numero) {
				temperatura = new Temperatura();
				temperatura.setId(rs.getInt(1));
				temperatura.setValor(rs.getDouble(2));
				//coloca no vetor
				temperaturas[i] = temperatura;
				i++;
 			}
			if(i < temperaturas.length){
				//como lancar uma nova excecao em Java
				throw new Exception("Existem apenas "+i+" temperaturas na tabela.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}
		return temperaturas;
	}
	//implementar
	public double media(Temperatura[] temps){
		//implementar, Nan e' Not a Number
		return Double.NaN;
	}
	public double maior(Temperatura[] temps){
		//implementar
		return Double.NaN;
	}
	public double menor(Temperatura[] temps){
		//implementar
		return Double.NaN;
	}
}