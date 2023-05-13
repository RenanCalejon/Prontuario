# Prontuario

API da aplicação para controle de consultas medicas.

## Endpoints

- Adicionar Paciente
    - [Cadastrar Paciente](#cadastrar-paciente)
    - Apagar paciente
    - Alterar paciente
    - Listar todos os pacientes
    - [Detalhar um paciente](#detalhar-paciente)
- Efetua Prontuario
    - Apagar Prontuario
    - Lista todos Prontuarios
- Adicionar Clinico
    - [Cadastrar clinico](#cadastrar-clinico)
    - Apagar clinico
    - Alterar clinico
    - Listar todos os clinicos
    - [Detalhar um clinico](#detalhar-clinico)
- Agendar Consulta
    - Apagar Consulta
    - Alterar Consulta
    - Lista todas as Consultas

---

### Cadastrar Paciente

`POST` /Prontuario/api/paciente{id}

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|----
| paciente_id | inteiro | sim | o id do paciente cadastrado
| nome | texto | sim | informação para saber cadastrar o Nome do paciente
| cpf | inteiro | sim | informação para saber o CPF do paciente
| data de nascimento | data | sim | a data em que o paciente nasceu
| sexo | inteiro | sim | informação para saber o sexo do paciente
| e-mail | texto | sim | informação para saber o  e-mail do paciente
| telefone | texto | sim | informação para saber o telefone do paciente
  
  **Exemplo de corpo de requisição**

```js 
{   
    paciente_id : 1,
    nome: 'Kanye West',
    cpf: 647.412.643-43,
    data de nascimento : '1985-01-15',
    sexo: 'Masculino',
    e-mail: 'KanyeYeezus@hotmail.com',
    telefone: (87) 3148-6883
}
```

**Respostas**

| código | descrição
|-|-
|201| Dados do paciente cadastrado com sucesso
|400| a validação dos campos falhou

### Detalhar paciente

`GET` /gestanca/api/paciente/{id}

**Respostas**

| código | descrição
|-|-
|200| os dados do paciente foram retornados no corpo da resposta
|404| não existe paciente com o id informado


**Detalhamento do Paciente**

```js 

{
   paciente :{
        paciente_id : 1,
        nome: 'Kanye West',
}
    cpd: 647.412.643-43,
    data de nascimento
    sexo: 'Masculino',
    e-mail Paciente:{
        e-mail:'KanyeYeezus@hotmail.com', 
        e-mail: 'Kanye@gmail.com'
    }
    e-mail:'KanyeYeezus@hotmail.com',   
    telefone: (84) 3148-6883 ,

}
```
---
### Cadastrar Clinico

`POST` /Prontuario/api/clinico{id}

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|----
| clinico_id | inteiro | sim | o id do clinico cadastrado
| nome | texto | sim | informação para saber cadastrar o Nome do clinico
| cpf | inteiro | sim | informação para saber o CPF do clinico
| data de nascimento | data | sim | a data em que o clinico nasceu
| e-mail | texto | sim | informação para saber o  e-mail do clinico
| especializacao | texto | sim | informação para saber a especialização do clinico
  
  **Exemplo de corpo de requisição**

```js 
{
    clinico_id : 1 ,
    nome: 'Doctor Dre',
    cpf: 311.795.885-17,
    data de nascimento : '1970-01-15',
    sexo: 'Masculino',
    e-mail: 'DreWestSide@gmail.com',
    especialização: 'Cardiologista'
}
```

**Respostas**

| código | descrição
|-|-
|201| Dados do clinico cadastrado com sucesso
|400| a validação dos campos falhou


### Detalhar clinico

`GET` /gestanca/api/clinico/{id}

**Respostas**

| código | descrição
|-|-
|200| os dados do clinico foram retornados no corpo da resposta
|404| não existe clinico com o id informado


**Detalhamento do Clinico**

```js 

{
   clinico :{
        clinico_id : 1,
        nome: 'Doctor Dre',
}
    cpf: 311.795.885-17,
    data de nascimento : '1970-01-15',
    sexo: 'Masculino',
    e-mail: 'DreWestSide@gmail.com',
    especialização:{
        especialização: 'Cardiologista'
        descrição :'especialidade médica que se ocupa do diagnóstico e tratamento das doenças que acometem o coração e componentes do sistema circulatório'
    }

}

```


