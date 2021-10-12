<h1 align="center"> App Store</h1>
<p align="center">O App Store é um projeto piloto de aplicativo com função de registrar as vendas de uma hipotética loja.</p>
<h4 align="center"> Em desenvolvimento</h4>
<h5 align="center"> Autor: Natália Siquara</>


### Atividade Principal

Na tela principal, o vendedor faz o login para ter acesso as funções do app. 

<div align="center">
<img src="https://user-images.githubusercontent.com/90353434/137038856-cf520626-b75e-4b41-9bcf-1c7264a06164.jpeg" width="300px" />
</div>

Para esse app, são cadastrados dois vendedores. Para efeito de teste, a senha para ambos foram definidas como "123456", e os emails os seguintes:

<div align="center">
<img src="https://user-images.githubusercontent.com/90353434/137039177-a4ee4202-9b92-44a3-8b78-dcd429e63195.jpg" width="700px" />
</div>

### Ativade Menu

 Nessa atividade, o usuário pode escolher, a partir de 4 botões, a qual atividade será encaminhado.  
 
 <div align="center">
<img src="https://user-images.githubusercontent.com/90353434/137040471-90090fac-3c86-4c9e-9c53-bb74c62bd93d.jpeg" width="300px" />
</div>

### Atividade New Sale

 Nessa atividade, o usuário pode cadastrar uma nova venda e salvar no Firebase. São salvas as seguintes informações:  nome do comprador, o produto e o valor. 
 
 <div align="center">
<img src="https://user-images.githubusercontent.com/90353434/137040290-11c91df9-9bd6-4c1c-877b-15eda83cad01.jpeg" width="300px" />
</div>

As informações são salvas da seguinte forma no console do Firebase.

 <div align="center">
<img src="https://user-images.githubusercontent.com/90353434/135627868-9ca98de4-1ce7-43a9-bfac-702060e11621.png" width="400px" />
</div>


### Atividade  Sale History 

Atividade History Sale: Nessa atividade, é exibido o histórico de vendas.

 <div align="center">
<img src="https://user-images.githubusercontent.com/90353434/137040395-c391e573-8e6b-45b1-a34c-853e344195ea.jpeg" width="300px" />
</div>

### Atividade  Delete a Sale

 Nessa atividade, a partir do nome do comprador, é possível deletar uma venda.
 
  <div align="center">
<img src="https://user-images.githubusercontent.com/90353434/137040702-02daf26a-46fa-46c8-a431-2118f08e0e24.jpeg" width="300px" />
</div>

### Atividade  Registre the product

 Nessa atividade, o usuário pode fotografar o produto, o qual será exibido na imagemview e o seu URI no textview.
 
  <div align="center">
<img src="https://user-images.githubusercontent.com/90353434/137040873-3048ca1f-3a98-4e24-baa8-eeb7ad5ce328.jpeg" width="300px" />
</div>

A imagem será salva no Storage. 

  <div align="center">
<img src="https://user-images.githubusercontent.com/90353434/137041102-b1e5dae5-d3e5-4548-a373-7bc12052febc.jpg" width="300px" />
</div>

 ### Atualizações

- Melhoria na harmonização das cores.
- Logo e icone do aplicativo.
- Autenticação firebase.
- Função de tirar foto e exibir.
- Crashlytics
- Storage.
 
 ### Erros e possíveis melhorias

- Melhoria do Design
- Correção da Classe Toast, visto que a mensagem não está sendo exibida.
- Correção na Atividade New sale, pois os campos não são limpos após o registro.
- Erro da Atividade Delete, pois não está funcionando.  Ao clicar no botão “Delete” a função não é executada.
- Falta na implementação do Broadcast/Service.
- Erro na obtenção do URL do Storage.

### Tecnologia Utilizada
- Java

