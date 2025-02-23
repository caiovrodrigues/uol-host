@import com.caio.uol.domain.enumeration.Time
@import java.util.Map
@import java.util.Objects

@param Map<String, String> erros = null
@param Boolean alertar = false

<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Cadastro jogador - UOL</title>
    <style>
        *{
            font-family: cursive;
        }
        .content{
            max-width: fit-content;
            display:flex;
            flex-flow: column wrap;
            border: 1px solid #ccc;
            padding: 2rem;
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
    <script defer>
        if(${alertar}){
            alert("hello world")
        }
    </script>
</head>
<body>
<div class="content">
    <h3 style="text-align:center; margin: 0 0 20px 0">Cadastro do jogador UOL</h3>
    <form method="POST" action="/jogador">
        <table>
            <tbody>
                <tr>
                    <td>Nome: </td>
                    <td><input type="text" name="nome"/></td>
                </tr>
                <tr>
                    <td>E-mail: </td>
                    <td><input type="text" name="email"/></td>
                </tr>
                <tr>
                    <td>Telefone: </td>
                    <td><input type="text" name="telefone"/></td>
                </tr>
                <tr>
                    <td>Grupo: </td>
                    <td>
                        <div>
                            <input type="radio" id="ligaDaJustica" value="${Time.LIGA_DA_JUSTICA.getName()}" name="time" />
                            <label for="ligaDaJustica">Liga da justiça</label>
                        </div>
                        <div>
                            <input type="radio" id="vingadores" value="${Time.VINGADORES.getName()}" name="time" />
                            <label for="vingadores">Vingadores</label>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>

        @if(Objects.nonNull(erros))
            @for(Map.Entry<String, String> erro : erros.entrySet())
                <p style="color:red">Campo ${erro.getKey()}: ${erro.getValue()}</p>
            @endfor
        @endif

        <div style="display: flex">
            <button type="submit">Cadastrar</button>
            <button type="button" onclick="javascript:window.location.href='players'">Listar jogadores</button>
        </div>

    </form>
</div>
</body>
</html>
