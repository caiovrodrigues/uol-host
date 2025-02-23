@import com.caio.uol.domain.Jogador
@import java.util.List
@import java.util.Objects

@param List<Jogador> jogadores = null


<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>UOL HOST</title>
    <style>
        *{
            font-family: cursive;
        }
        table{
            padding: 0;
        }
        th{
            background-color: #a3afc9;
        }
        th, td{
            padding: .2rem 1rem;
        }
        th:first-child{
            border-top-left-radius:10px;
        }
        th:last-child{
            border-top-right-radius:10px;
        }
        tbody>tr:nth-child(odd){
            background-color: #f5f6f8;
        }

        .content{
            max-width: 1100px;
            display:flex;
            flex-flow: column wrap;
            border: 1px solid #ccc;
            padding: 2rem 0;
            margin: auto;
        }

        button{
            background:none;
            max-width: fit-content;
            margin: auto;
            padding: 8px 20px;
            cursor: pointer;
            margin-top: 10px;
            box-shadow: 3px 3px black;
            transition: .2s linear;
        }

        button:hover{
            box-shadow: none;
        }

    </style>
</head>
<body>
    <div class="content">
        @if(Objects.nonNull(jogadores))
            <h3 style="text-align:center; margin: 0 0 20px 0">Lista de jogadores</h3>
            <table>
                <thead>
                    <th>Nome</th>
                    <th>E-mail</th>
                    <th>Telefone</th>
                    <th>Codinome</th>
                    <th>Grupo</th>
                </thead>
                <tbody>
                @for(Jogador jog : jogadores)
                    <tr>
                        <td>${jog.getNome()}</td>
                        <td>${jog.getEmail()}</td>
                        <td>${jog.getTelefone()}</td>
                        <td style="display:flex; justify-content:space-between; align-items:center;">
                            ${jog.getCodinome()} <img width="40" height="40" style="object-fit:cover; object-position:top; border-radius:50%; box-shadow: 0px 0px 10px #a3afc9;" src="${jog.getHeroImageURL()}">
                        </td>
                        <td>${jog.getTime().getName()}</td>
                    </tr>
                @endfor
                </tbody>
            </table>
        @endif

        <button onclick="javascript:window.location.href='cadastro'">Novo jogador</button>
    </div>

</body>
</html>