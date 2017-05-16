package controle;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class AtendimentoHelper {

	public static List<AtendimentoDTO> getListaAtendimentoBP(JSONArray jsonArray) {

		List<AtendimentoDTO> atendimentos = new ArrayList<AtendimentoDTO>();
		
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject js = jsonArray.getJSONObject(i);
			JSONObject js3 = null;
			if (js.has("unidade")) {
                JSONObject unidadeJson = js.getJSONObject("unidade");
                if (unidadeJson.has("municipio")) {
                    js3 = unidadeJson.getJSONObject("municipio");
                }
            }

            if (js.has("cliente")) {
                JSONObject jsonClient = js.getJSONObject("cliente");
                if (jsonClient.has("enderecoList") && !jsonClient.isNull("enderecoList")) {
                    JSONArray enderecoArray = jsonClient.getJSONArray("enderecoList");
                    if (enderecoArray.length() > 0) {
                        JSONObject js2 = enderecoArray.getJSONObject(0);
                        if (js2.has("municipio")) {
                            js3 = js2.getJSONObject("municipio");
                        }
                    }
                }
            }
			JSONObject jsUnidade = js.getJSONObject("unidade");
			JSONObject jsUnidadePai = jsUnidade.getJSONObject("unidadePai");
			JSONObject jsCliente = js.getJSONObject("cliente");
			JSONObject jsApl = js.getJSONObject("apl");
			JSONObject jsSetor = js.getJSONObject("setor");

			atendimentos.add(new AtendimentoDTO(
					js.get("id").toString(),
					js.getInt("numero") + "",
					// numero,
					js.getJSONObject("atendimentoStatus").getString("descricao"),
					// status,
					js.getString("titulo"),
					// titulo,
					jsUnidade.getString("descricao"),
					// deUnidade,
					jsUnidadePai.getString("descricao"),
					// regional,
					//jsarray.getJSONObject("cliente").getJSONObject("enderecoList").getJSONObject("municipio").getString("descricao"),
					js3.getString("descricao"),
					// municipio,
					jsCliente.getString("cpfcnpj"),
					// cnpjCliente,
					jsCliente.getString("razaoSocial"),
					// razaosocial,
					jsCliente.getString("nome"),
					// fantasia,
					jsCliente.getString("numeroDeFuncionarios"),
					// numeroFuncionarios,
					js.getJSONObject("produtoRegional").getString("nome"),
					// vlrFinanceiro
					jsApl.getString("descricao"),
					// vlrEconomico,
					js.getDouble("vlrFinanceiro"),
					// produtoRegional,
					js.getDouble("vlrEconomico"),
					// desricao apl
					jsSetor.getString("descricao"),
					// desricao setor
					js.getString("dataEmissao"),
					// desricao setor
					js.isNull("dataInicio") ? "" : js.getString("dataInicio"),
					// produtividade,
					js.isNull("produtividade") ? "" : ( js.getDouble("produtividade") + ""), 
					// movimentacao,
					js.isNull("movimentacao") ? "" : (js.getDouble("movimentacao") + ""),
					// qualidade,
					js.isNull("qualidade") ? "" : (js.getDouble("qualidade") + ""),
					// retornoPrograma
					js.isNull("retornoPrograma") ? "" : (js.getDouble("retornoPrograma") + ""),
					//cnae do cliente
					jsCliente.isNull("cnae") ? "" : jsCliente.getString("cnae"),
					//dataAceitacao
					js.isNull("dataAceitacao") ? "" : js.getString("dataAceitacao"),
					//dataConclusao
					js.isNull("dataConclusao") ? "" : js.getString("dataConclusao") 
			));
		}
		
		return atendimentos;
	}

}
