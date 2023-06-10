package com.mycompany.crud;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import dao.lugarDAO;
import dao.pessoaDAO;
import dao.veiculoDAO;
import dao.viagemDAO;
import java.awt.Desktop.Action;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Lugar;
import models.Pessoa;
import models.Veiculo;
import models.Viagem;
/**
 *
 * @author mikaela.begotti
 */
public class TelaController {
    @FXML
    private TextField textoNome;
    @FXML
    private TextField textoCPF;
    @FXML
    private DatePicker textoDataNasc;
    
    @FXML
    private TextField textoNomeV;
    @FXML
    private TextField textoCor;
    @FXML
    private TextField textoAno;
    
    @FXML
    private TextField textoCidade;
    @FXML
    private TextField textoEstado;
    @FXML
    private TextField textoPais;
    
    @FXML
    private Button btnSalvarPessoa;
    @FXML
    private Button btnApagarPessoa;
    @FXML
    private Button btnAtualizarPessoa;
    
    @FXML
    private Button btnSalvarVeiculo;
    @FXML
    private Button btnApagarVeiculo;
    @FXML
    private Button btnAtualizarVeiculo;
    
    @FXML
    private Button btnSalvarLugar;
    @FXML
    private Button btnApagarLugar;
    @FXML
    private Button btnAtualizarLugar;
    
    @FXML
    private Button btnSalvarViagem;
    @FXML
    private Button btnApagarViagem;
    @FXML
    private Button btnAtualizarViagem;
    
    @FXML 
    private Button btnLimparPessoa;
    @FXML 
    private Button btnLimparVeiculo;
    @FXML 
    private Button btnLimparLugar;
    @FXML 
    private Button btnLimparViagem;
    
    @FXML
    private ComboBox<String> pessoaSel;
    @FXML
    private ComboBox<String> veiculoSel;
    @FXML
    private ComboBox<String> lugarSel;
    
    @FXML
    private TableView<Pessoa> tabelaPessoa;
    @FXML
    private TableColumn<Pessoa, String> tabelaNome;
    @FXML
    private TableColumn<Pessoa, String> tabelaCPF;
    @FXML
    private TableColumn<Pessoa, String> tabelaData;
    
    @FXML
    private TableView<Veiculo> tabelaVeiculo;
    @FXML
    private TableColumn<Veiculo, String> tabelaNomeV;
    @FXML
    private TableColumn<Veiculo, String> tabelaCor;
    @FXML
    private TableColumn<Veiculo, Integer> tabelaAno;
    
    @FXML
    private TableView<Lugar> tabelaLugar;
    @FXML
    private TableColumn<Lugar, String> tabelaPais;
    @FXML
    private TableColumn<Lugar, String> tabelaCidade;
    @FXML
    private TableColumn<Lugar, String> tabelaEstado;
    
    @FXML
    private TableView<Viagem> tabelaViagem;
    @FXML
    private TableColumn<Viagem, String> tabelaPessoaV;
    @FXML
    private TableColumn<Viagem, String> tabelaLugarV;
    @FXML
    private TableColumn<String, String> tabelaVeiculoV;
    
    private List<String> pess = new ArrayList<>();
    private ObservableList<String> opPess = FXCollections.observableArrayList();
    
    private List<String> ve = new ArrayList<>();
    private ObservableList<String> opVe = FXCollections.observableArrayList();
    
    private List<String> lu = new ArrayList<>();
    private ObservableList<String> opLu = FXCollections.observableArrayList();
    
    private List<String> vi = new ArrayList<>();
    private ObservableList<String> opVi = FXCollections.observableArrayList();
    
    private List<Pessoa> lispessoas= new ArrayList<>();
    private ObservableList<Pessoa> observablelistpessoas = FXCollections.observableArrayList();
    
    private List<Veiculo> lisveiculos= new ArrayList<>();
    private ObservableList<Veiculo> observablelistveiculos = FXCollections.observableArrayList();
    
    private List<Lugar> lislugares= new ArrayList<>();
    private ObservableList<Lugar> observablelistlugares = FXCollections.observableArrayList();
    
    private List<Viagem> lisviagens= new ArrayList<>();
    private ObservableList<Viagem> observablelistviagens = FXCollections.observableArrayList();
    
    
    int valorDoItemSelecionado;
    int valorDoItemSelecionado2;
    int valorDoItemSelecionado3;
    int valorDoItemSelecionado4;
    Pessoa item;
    Veiculo itemV;
    Lugar itemL;
    Viagem itemVG;
    public void initialize() {
        textoAno.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            textoAno.setText(newValue.replaceAll("[^\\d]", ""));
        }
        });
        tabelaPessoa.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    item = tabelaPessoa.getSelectionModel().getSelectedItem();
                    valorDoItemSelecionado = item.getIdPessoa();
                    textoNome.setText(item.getNomePessoa());
                    textoCPF.setText(item.getCpf());
                    String dataStr = item.getDataNascimento();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate dataNascimento = LocalDate.parse(dataStr, formatter);
                    textoDataNasc.setValue(dataNascimento);
                    System.out.println(valorDoItemSelecionado);
                    
                }
                
            }
        });
        tabelaVeiculo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    itemV = tabelaVeiculo.getSelectionModel().getSelectedItem();
                    valorDoItemSelecionado2 = itemV.getIdVeiculo();
                    textoNomeV.setText(itemV.getNomeVeiculo());
                    textoCor.setText(itemV.getCorVeiculo());
                    String ano = Integer.toString(itemV.getAno());
                    textoAno.setText(ano);
                    System.out.println(valorDoItemSelecionado2);
                }   
                }
        });
        tabelaLugar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    itemL = tabelaLugar.getSelectionModel().getSelectedItem();
                    valorDoItemSelecionado3 = itemL.getIdLugar();
                    textoPais.setText(itemL.getPais());
                    textoEstado.setText(itemL.getEstado());
                    textoCidade.setText(itemL.getCidade());
                    System.out.println(valorDoItemSelecionado3);
                }   
                }
        });
        tabelaViagem.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    itemVG = tabelaViagem.getSelectionModel().getSelectedItem();
                    valorDoItemSelecionado4 = itemVG.getIdViagem();
                    pessoaSel.setValue(itemVG.getPessoa());
                    veiculoSel.setValue(itemVG.getVeiculo());
                    lugarSel.setValue(itemVG.getLugar());
                    System.out.println(valorDoItemSelecionado4);
                }   
                }
        });
        carregarTabelaPessoa();
        carregarTabelaVeiculo();
        carregarTabelaLugar();
        carregarTabelaViagem();
        
    }
    
    @FXML 
    public void salvarPessoa(ActionEvent event){
        lispessoas.clear();
        pessoaDAO pessoa =  new pessoaDAO();
        Pessoa pessoaIns =  new Pessoa();
        
        pessoaIns.setNomePessoa(textoNome.getText());
        pessoaIns.setCpf(textoCPF.getText());
        pessoaIns.setDataNascimento(textoDataNasc.getValue().toString());
        pessoa.save(pessoaIns);
        tabelaPessoa.refresh();
        carregarTabelaPessoa();
    }
    
    @FXML 
    public void salvarVeiculo(ActionEvent event){
        lisveiculos.clear();
        veiculoDAO veiculo =  new veiculoDAO();
        Veiculo veiculoIns =  new Veiculo();
        
        veiculoIns.setNomeVeiculo(textoNomeV.getText());
        veiculoIns.setCorVeiculo(textoCor.getText());
        veiculoIns.setAno(Integer.parseInt(textoAno.getText()));
        veiculo.save(veiculoIns);
        tabelaVeiculo.refresh();
        carregarTabelaVeiculo();
    }
    
    @FXML 
    public void salvarLugar(ActionEvent event){
        lislugares.clear();
        lugarDAO lugar =  new lugarDAO();
        Lugar lugarIns =  new Lugar();
        
        lugarIns.setPais(textoPais.getText());
        lugarIns.setEstado(textoEstado.getText());
        lugarIns.setCidade(textoCidade.getText());
        lugar.save(lugarIns);
        tabelaLugar.refresh();
        carregarTabelaLugar();
    }
    
    @FXML 
    public void salvarViagem(ActionEvent event){
        lisviagens.clear();
        viagemDAO viagem =  new viagemDAO();
        Viagem viagemIns =  new Viagem();
        
        viagemIns.setPessoa(pessoaSel.getValue());
        viagemIns.setVeiculo(veiculoSel.getValue());
        viagemIns.setLugar(lugarSel.getValue());
        viagem.save(viagemIns);
        tabelaViagem.refresh();
        carregarTabelaViagem();
    }
    
    @FXML
    public void carregarTabelaPessoa(){
        pessoaDAO pessoadao = new pessoaDAO();
        Pessoa pessoa = new Pessoa();
        lispessoas.clear();
        lispessoas.addAll(pessoadao.getPessoas());
        observablelistpessoas.setAll(lispessoas);
        tabelaPessoa.setItems(observablelistpessoas);
        tabelaNome.setCellValueFactory(new PropertyValueFactory<>("nomePessoa"));
        tabelaCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tabelaData.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        tabelaPessoa.refresh();
        
        }
    
    @FXML
    public void carregarTabelaVeiculo(){
        veiculoDAO veiculodao = new veiculoDAO();
        lisveiculos.clear();
        lisveiculos.addAll(veiculodao.getVeiculos());
        observablelistveiculos.setAll(lisveiculos);
        tabelaVeiculo.setItems(observablelistveiculos);
        tabelaNomeV.setCellValueFactory(new PropertyValueFactory<>("nomeVeiculo"));
        tabelaCor.setCellValueFactory(new PropertyValueFactory<>("corVeiculo"));
        tabelaAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        tabelaVeiculo.refresh();
        
        }
    
    @FXML
    public void carregarTabelaLugar(){
        lugarDAO lugardao = new lugarDAO();
        lislugares.clear();
        lislugares.addAll(lugardao.getLugares());
        observablelistlugares.setAll(lislugares);
        tabelaLugar.setItems(observablelistlugares);
        tabelaPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
        tabelaEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        tabelaCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        tabelaLugar.refresh();
        
        }
    
    @FXML
    public void carregarTabelaViagem(){
        viagemDAO viagemdao = new viagemDAO();
        lisviagens.clear();
        lisviagens.addAll(viagemdao.getViagens());
        observablelistviagens.setAll(lisviagens);
        tabelaViagem.setItems(observablelistviagens);
        tabelaPessoaV.setCellValueFactory(new PropertyValueFactory<>("pessoa"));
        tabelaLugarV.setCellValueFactory(new PropertyValueFactory<>("lugar"));
        tabelaVeiculoV.setCellValueFactory(new PropertyValueFactory<>("veiculo"));
        
        tabelaViagem.refresh();
        
        }
    
    @FXML
    private void btnApagar(ActionEvent event) {
        lispessoas.clear();
        System.out.println("click");
        pessoaDAO pessoaDao = new pessoaDAO();
        pessoaDao.delete(item);
        tabelaPessoa.getItems().remove(item);
        tabelaPessoa.refresh();

        carregarTabelaPessoa();
    }
    
    @FXML
    private void btnApagar2(ActionEvent event) {
        lisveiculos.clear();
        System.out.println("click");
        veiculoDAO veiculoDao = new veiculoDAO();
        veiculoDao.delete(itemV);
        tabelaVeiculo.getItems().remove(itemV);
        tabelaVeiculo.refresh();

        carregarTabelaVeiculo();
    }
    
    @FXML
    private void btnApagar3(ActionEvent event) {
        lislugares.clear();
        System.out.println("click");
        lugarDAO lugarDao = new lugarDAO();
        lugarDao.delete(itemL);
        tabelaLugar.getItems().remove(itemL);
        tabelaLugar.refresh();

        carregarTabelaLugar();
    }
    
    @FXML
    private void btnApagar4(ActionEvent event) {
        lisviagens.clear();
        System.out.println("click");
        viagemDAO viagemDao = new viagemDAO();
        viagemDao.delete(itemVG);
        tabelaViagem.getItems().remove(itemVG);
        tabelaViagem.refresh();

        carregarTabelaViagem();
    }
    
    @FXML
    private void btnAtualizar(ActionEvent event){
        lispessoas.clear();
        pessoaDAO pessoaDao = new pessoaDAO();
        Pessoa pessoa = pessoaDao.getPessoaById(item.getIdPessoa());
        pessoa.setNomePessoa(textoNome.getText());
        pessoa.setCpf(textoCPF.getText());
        LocalDate dataStr = textoDataNasc.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataNascimento = LocalDate.parse(dataStr.toString(), formatter);
        pessoa.setIdPessoa(item.getIdPessoa());
        
        pessoa.setDataNascimento(dataNascimento.toString());
        pessoaDao.update(pessoa);
        
        tabelaPessoa.refresh();
        carregarTabelaPessoa();
    }
    
    @FXML
    private void btnAtualizar2(ActionEvent event){
        lisveiculos.clear();
        veiculoDAO veiculoDao = new veiculoDAO();
        Veiculo veiculo = veiculoDao.getVeiculoById(itemV.getIdVeiculo());
        veiculo.setNomeVeiculo(textoNomeV.getText());
        veiculo.setCorVeiculo(textoCor.getText());
        veiculo.setAno(Integer.parseInt(textoAno.getText()));
        veiculo.setIdVeiculo(itemV.getIdVeiculo());
   
        veiculoDao.update(veiculo);
        tabelaVeiculo.refresh();
        carregarTabelaVeiculo();
    }
    
    @FXML
    private void btnAtualizar3(ActionEvent event){
        lislugares.clear();
        lugarDAO lugarDao = new lugarDAO();
        Lugar lugar = lugarDao.getLugarById(itemL.getIdLugar());
        lugar.setPais(textoPais.getText());
        lugar.setEstado(textoEstado.getText());
        lugar.setCidade(textoCidade.getText());
        lugar.setIdLugar(itemL.getIdLugar());
   
        lugarDao.update(lugar);
        
        tabelaLugar.refresh();
        carregarTabelaLugar();
    }
    
    @FXML
    private void btnAtualizar4(ActionEvent event){
        lisviagens.clear();
        viagemDAO viagemDao = new viagemDAO();
        Viagem viagem = viagemDao.getViagemById(itemVG.getIdViagem());
        viagem.setPessoa(pessoaSel.getValue());
        viagem.setLugar(lugarSel.getValue());
        viagem.setVeiculo(veiculoSel.getValue());
        
        viagem.setIdViagem(itemVG.getIdViagem());
   
        viagemDao.update(viagem);
        
        tabelaViagem.refresh();
        carregarTabelaViagem();
    
    }
    
    @FXML
    private void btnLimparPessoa(ActionEvent event){
        textoNome.setText("");
        textoCPF.setText("");
        textoDataNasc.setValue(null);
    }
    
    @FXML
    private void btnLimparVeiculo(ActionEvent event){
        textoCor.setText("");
        textoNomeV.setText("");
        textoAno.setText("");
    }
    
    @FXML
    private void btnLimparLugar(ActionEvent event){
        textoPais.setText("");
        textoEstado.setText("");
        textoCidade.setText("");
    }
    
    @FXML
    private void btnLimparViagem(ActionEvent event){
        pessoaSel.setValue(null);
        veiculoSel.setValue(null);
        lugarSel.setValue(null);
    }
    
    @FXML
    public void fornecePessoa() {
        pessoaDAO pessoa = new pessoaDAO();
        pess.clear();
        for (Pessoa pessoa1 : pessoa.getPessoas()) {
            pess.add(pessoa1.getNomePessoa());
        }
        opPess.setAll(pess);
        getSelecaoPessoa().setItems(opPess);
    }
    
    @FXML
    public void forneceVeiculo() {
        veiculoDAO veiculo = new veiculoDAO();
        pess.clear();
        for (Veiculo veiculo1 : veiculo.getVeiculos()) {
            pess.add(veiculo1.getNomeVeiculo());
        }
        opPess.setAll(pess);
        getSelecaoVeiculo().setItems(opPess);
    }
    
    @FXML
    public void forneceLugar() {
        lugarDAO lugar = new lugarDAO();
        lu.clear();
        for (Lugar lugar1 : lugar.getLugares()) {
            lu.add(lugar1.getCidade());
        }
        opLu.setAll(lu);
        getSelecaoLugar().setItems(opLu);
    }
    
     public ComboBox<String> getSelecaoPessoa() {
        return pessoaSel;
    }
    public ComboBox<String> getSelecaoVeiculo() {
        return veiculoSel;
    }
    public ComboBox<String> getSelecaoLugar() {
        return lugarSel;
    }

 
}


        
 
     

