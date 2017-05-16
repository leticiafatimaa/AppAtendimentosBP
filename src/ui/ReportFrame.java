package ui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Console;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;

import security.SGTBase;
import security.SearchSGT;
import controle.AtendimentoDTO;
import controle.AtendimentoHelper;
import controle.PrevisaoReceitaDTO;
import controle.PrevisaoReceitaHelper;

public class ReportFrame extends JFrame {

	private static final long serialVersionUID = 1533617104550952923L;
	
	private JMenuBar menu = new JMenuBar();
	private JMenu bpMenu = new JMenu("B + P");
	private JMenuItem atendimentoBP = new JMenuItem("Atendimento");
	private JMenuItem previsaoReceitaBP = new JMenuItem("Fontes Pagadoras");
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy");
	
    private JProgressBar progressBar;
    private JTextArea taskOutput;
    private Task task = new Task();
    
	public String path, excelFileName;
	public boolean erro = false;
	
	
	public ReportFrame( boolean visible, final String user, final String password) {
		this.setVisible( visible );
		
		progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
 
        taskOutput = new JTextArea(22, 50);
        taskOutput.setMargin(new Insets(5,5,5,5));
        taskOutput.setEditable(false);
		
        this.add(new JScrollPane(taskOutput), BorderLayout.CENTER);
        this.add(progressBar, BorderLayout.NORTH);
        
		bpMenu.add( atendimentoBP );
		bpMenu.add(previsaoReceitaBP  );
		menu.add( bpMenu );
		
		JMenu helpM = new JMenu("Help");
		helpM.setMnemonic(KeyEvent.VK_H);
		
		JMenuItem sobre = new JMenuItem("Sobre");
		sobre.setMnemonic(KeyEvent.VK_S);
		sobre.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Relatórios B+P SGT.\nEquipe SGT.", "SGT Cliente Report", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		helpM.add( sobre );
		menu.add( helpM );
		
		atendimentoBP.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					atendimentoBP.setEnabled(false);
					
					progressBar.setValue(0);
					
			        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			        //Instances of javax.swing.SwingWorker are not reusuable, so
			        //we create new instances as needed.
			        task.addPropertyChangeListener(new PropertyChangeListener() {
			            
			        	/**
			             * Invoked when task's progress property changes.
			             */
			            public void propertyChange(PropertyChangeEvent evt) {
			                if ("progress" == evt.getPropertyName()) {
			                    int progress = (Integer) evt.getNewValue();
			                    if ( progress < 100 ) {
			                    	progressBar.setValue(progress);
			                    	taskOutput.append(
			                    			String.format("Gerando Arquivo %d%% .\n", task.getProgress() )
			                    	);
			                    }
			                } 
			            }
			        	
			        }); 
			        
					geraRelatorioAtendimentoBP(user, password);
				} catch (IOException eio) {
					erro = false;
					JOptionPane.showMessageDialog(null, "Erro ao gerar o xlsx", "Erro", JOptionPane.ERROR_MESSAGE);
					eio.printStackTrace();
				}
			}
		});
		
		previsaoReceitaBP.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					previsaoReceitaBP.setEnabled(false);
					
					progressBar.setValue(0);
					
			        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			        //Instances of javax.swing.SwingWorker are not reusuable, so
			        //we create new instances as needed.
			        task.addPropertyChangeListener(new PropertyChangeListener() {
			            
			        	/**
			             * Invoked when task's progress property changes.
			             */
			            public void propertyChange(PropertyChangeEvent evt) {
			                if ("progress" == evt.getPropertyName()) {
			                    int progress = (Integer) evt.getNewValue();
			                    if ( progress < 100 ) {
			                    	progressBar.setValue(progress);
			                    	taskOutput.append(
			                    			String.format("Gerando Arquivo %d%% .\n", task.getProgress() )
			                    	);
			                    }
			                } 
			            }
			        	
			        }); 
			        
			        gerarPrevisaoReceitaAtendimentoBP(user, password);
				} catch (IOException eio) {
					erro = true;
					JOptionPane.showMessageDialog(null, "Erro ao gerar o xlsx", "Erro", JOptionPane.ERROR_MESSAGE);
					eio.printStackTrace();
				}
				
			}
		});
		
		this.setTitle("Relatorios SGT");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar( menu );
		
		
		//this.setSize(300, 400);
		this.pack();
		this.setMinimumSize( this.getPreferredSize() );
		
		this.setLocationRelativeTo( null );
	}
	
	public void gerarPrevisaoReceitaAtendimentoBP(String user, String password) throws IOException {
		path = System.getProperty("user.home");
		String excelFileName = "";
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		chooser.setDialogTitle("Selecione o local para salvar ...");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION) {
			task.execute();
			path = chooser.getSelectedFile().getPath();

			SearchSGT sgt = new SearchSGT();

			JSONArray receitasJSON = sgt.callSGT(
							SGTBase.getBase(SGTBase.P) 	+
							"/gestao/PrevisaoReceita?atendimento.produtoRegional.produtoNacional.id=193",
							user,
							password);

			excelFileName = path + File.separator + "RelatorioPrevisaoReceitaBP-" + sdf.format(new Date()) + " .xlsx";// name of excel file
			this.excelFileName = excelFileName;

			String sheetName = "PrevisaoReceita";// name of sheet

			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet(sheetName);

			Map<String, Object[]> newData = new TreeMap<String, Object[]>();
			// Cabecalho
			newData.put("1", new Object[] { 
					"ID_ATENDIMENTO", //
					"VALOR",
					"CPF_CNPJ",
					"NOME",
					"RAZAO_SOCIAL"

			});

			List<PrevisaoReceitaDTO> receitas = PrevisaoReceitaHelper.getListaPrevisaoReceitaBP( receitasJSON );

			int i = 2;

			for (PrevisaoReceitaDTO r : receitas) {
				newData.put(
						String.valueOf(i++),
						new Object[] {
							r.getIdAtendimento(),
							r.getValor(),
							r.getCpfcnpj(),
							r.getNome(),
							r.getRazaoSocial() });
			}

			Set<String> newRows = newData.keySet();
			int rownum = sheet.getFirstRowNum();

			for (String key : newRows) {
				XSSFRow row = sheet.createRow(rownum++);
				Object[] objArr = newData.get(key);
				int cellnum = 0;
				for (Object obj : objArr) {
					XSSFCell cell = row.createCell(cellnum++);
					if (obj instanceof String) {
						cell.setCellValue((String) obj);
					} else if (obj instanceof Boolean) {
						cell.setCellValue((Boolean) obj);
					} else if (obj instanceof Date) {
						cell.setCellValue((Date) obj);
					} else if (obj instanceof Double) {
						cell.setCellValue((Double) obj);
					}
				}
			}

			FileOutputStream fileOut = new FileOutputStream(excelFileName);

			// write this workbook to an Outputstream.
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
			wb.close();
			
			task.setDoneProgress();
			task.done();
	    	erro = false;
	    	
	    	JOptionPane.showMessageDialog(null, "Arquivo gerado com sucesso em \n " + excelFileName  ,"Sucesso", JOptionPane.INFORMATION_MESSAGE);
	    	
	    	taskOutput.append("Arquivo gerado com sucesso em " + excelFileName);
	    	
		} else {
	    	JOptionPane.showMessageDialog(null, "Selecione o local","Local", JOptionPane.INFORMATION_MESSAGE);
	    	enableMenusItens();
	    }
	}
	
	public void geraRelatorioAtendimentoBP(String user, String password) throws IOException {
		path = System.getProperty("user.home");
		JFileChooser chooser = new JFileChooser(); 
	    chooser.setCurrentDirectory( new File( System.getProperty("user.home") ) );
	    chooser.setDialogTitle("Selecione o local para salvar ...");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);
	    
	    if ( chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION ) { 
	    	task.execute();
	    	path = chooser.getSelectedFile().getPath();
	    	
	    	SearchSGT sgt = new SearchSGT();
	    	
	    	JSONArray atendimentosJSON = sgt.callSGT( SGTBase.getBase( SGTBase.P)  + "/gestao/Atendimento?produtoRegional.produtoNacional.id=193", user, password);
	    	
	    	String excelFileName = path + File.separator + "RelatorioAtendimentoBP-" + sdf.format(new Date() ) + " .xlsx";// name of excel file
	    	this.excelFileName = excelFileName;
	    	
	    	String sheetName = "AtendimentoBP";// name of sheet
	    	
	    	XSSFWorkbook wb = new XSSFWorkbook();
	    	XSSFSheet sheet = wb.createSheet(sheetName);
	    	
	    	Map<String, Object[]> newData = new TreeMap<String, Object[]>();
	    	//Cabecalho 
	    	newData.put("1", new Object[] {
	    			"ID", // 
	    			"NUMERO",//0
	    			"STATUS",//1
	    			"TITULO",//2
	    			"UNIDADE",//3
	    			"REGIONAL",//4
	    			"MUNICIPIO",
	    			"CNPJ",//5
	    			"CNAE",//6
	    			"RAZAOSOCIAL",//7
	    			"NOME FANTASIA",//8
	    			"FUCIONARIOS",//9
	    			"PRODUTO REGIONAL",//10
	    			"VALOR ECONOMICO",//11
	    			"VALOR FINANCEIRO",//12
	    			"APL",//13
	    			"SETOR",//14
	    			"DT_EMISSAO",//15
	    			"DT_INICIO_EXECUCAO",//16
	    			"PRODUTIVIDADE",//17
					"MOVIMENTACAO",//18
					"QUALIDADE",//19
					"RETORNO_PROGRAMA",//20
					"DT_ACEITE",//21
					"DT_CONCLUSAO"//22
					
	    	});
	    	
	    	List<AtendimentoDTO> atendimentos = AtendimentoHelper.getListaAtendimentoBP(atendimentosJSON);
	    	
	    	int i = 2;
	    	
	    	for (AtendimentoDTO a : atendimentos) {
	    		newData.put( 
	    				String.valueOf(i++),
	    				new Object[] {
	    					a.getId(),
	    					a.getNumero(),//0
	    					a.getStatus(),//1
	    					a.getTitulo(),//2
	    					a.getDeUnidade(),//3
	    					a.getRegional(),//4
	    					a.getMunicipio(),
	    					a.getCnpjCliente(),//5
	    					a.getCnae(),//6
	    					a.getRazaoSocial(),//7
	    					a.getFantasia(),//8
	    					a.getNumeroFuncionarios(),//9
	    					a.getProdutoRegional(),//10
	    					a.getVlrEconomico(),//11
	    					a.getVlrFinanceiro(),//12
	    					a.getDeApl(),//13
	    					a.getDeSetor(),//14
	    					a.getDataEmissao(),//15
	    					a.getDataInicio(),//16
	    					a.getProdutividade(),//17
	    					a.getMovimentacao(),//18
	    					a.getQualidade(),//19
	    					a.getRetornoPrograma(),//20
	    					a.getDataAceite(),//21
	    					a.getDataConclusao(),
	    					
	    					
	    				});
	    	}
	    	
	    	Set<String> newRows = newData.keySet();
	    	int rownum = sheet.getFirstRowNum();
	    	
	    	for (String key : newRows) {
	    		XSSFRow row = sheet.createRow(rownum++);
	    		Object[] objArr = newData.get(key);
	    		int cellnum = 0;
	    		for (Object obj : objArr) {
	    			XSSFCell cell = row.createCell(cellnum++);
	    			if (obj instanceof String) {
	    				cell.setCellValue((String) obj);
	    			} else if (obj instanceof Boolean) {
	    				cell.setCellValue((Boolean) obj);
	    			} else if (obj instanceof Date) {
	    				cell.setCellValue((Date) obj);
	    			} else if (obj instanceof Double) {
	    				cell.setCellValue((Double) obj);
	    			}
	    		}
	    	}
	    	
	    	FileOutputStream fileOut = new FileOutputStream(excelFileName);
	    	
	    	// write this workbook to an Outputstream.
	    	wb.write(fileOut);
	    	fileOut.flush();
	    	fileOut.close();
	    	wb.close();
	    	
	    	task.setDoneProgress();
	    	erro = false;
	    	
	    	taskOutput.append("Arquivo gerado com sucesso em " + excelFileName);
	    	
	    	JOptionPane.showMessageDialog(null, "Arquivo gerado com sucesso em \n " + excelFileName ,"Sucesso", JOptionPane.INFORMATION_MESSAGE);
	    	
	    } else {
	    	JOptionPane.showMessageDialog(null, "Selecione o local","Local", JOptionPane.INFORMATION_MESSAGE);
	    	enableMenusItens();
	    }
	}
	
	private void enableMenusItens() {
		atendimentoBP.setEnabled(true);
		previsaoReceitaBP.setEnabled(true);
    	setCursor(null); //turn off the wait cursor
	}
	
    class Task extends SwingWorker<Void, Void> {
    	
    	public void setDoneProgress() {
    		super.setProgress(100);
    		progressBar.setValue(100);
    	}
    	
        /*
         * Main task. Executed in background thread.
         */
        @Override
        public Void doInBackground() {
            Random random = new Random();
            int progress = 0;
            //Initialize progress property.
            super.setProgress(0);
            while (progress < 100) {
                //Sleep for up to one second.
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException ignore) {}
                //Make random progress.
                progress += random.nextInt(10);
                super.setProgress(Math.min(progress, 100));
            }
            return null;
        }
 
        /*
         * Executed in event dispatching thread
         */
        @Override
        public void done() {
            Toolkit.getDefaultToolkit().beep();
            enableMenusItens();
            
            if ( !erro ) {
            	progressBar.setValue(100);
            	taskOutput.append("Feito!\n");
            	taskOutput.append("Arquivo gerado com sucesso em " + excelFileName);
            }
        }
    }
}
