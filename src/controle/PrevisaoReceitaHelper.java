package controle;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class PrevisaoReceitaHelper {
	
	public static List<PrevisaoReceitaDTO> getListaPrevisaoReceitaBP(JSONArray jsonArray) {

		List<PrevisaoReceitaDTO> receitas = new ArrayList<PrevisaoReceitaDTO>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject js = jsonArray.getJSONObject(i);
//			JSONObject jsTipoPrevisaoReceita = js.getJSONObject("tipoPrevisaoReceita");
			JSONObject jsFontePagadora = js.isNull("fontePagadora") ? null : js.getJSONObject("fontePagadora");

			//atendimentos.add(new AtendimentoBpDTO(
			receitas.add( new PrevisaoReceitaDTO(
//					"ID_ATENDIMENTO", //
					js.getJSONObject("atendimento").get("id").toString(),
//					"VALOR",
					js.get("valor").toString(),
//					"CPF_CNPJ",
					jsFontePagadora == null ? "" : jsFontePagadora.get("cpfcnpj").toString(),
//					"NOME",
					jsFontePagadora == null ? "" : jsFontePagadora.get("nome").toString(),
//					"RAZAO_SOCIAL"
					jsFontePagadora == null ? "" : jsFontePagadora.get("razaoSocial").toString()
					));
					
		}
		return receitas;
	}
}
