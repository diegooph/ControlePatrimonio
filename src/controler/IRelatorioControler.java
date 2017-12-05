package controler;

import java.util.Date;
import java.util.List;

import entity.Relatorio;

public interface IRelatorioControler {

	List<Relatorio> listarRelatorios(Date datainicio, Date datafim);

}
