@import com.caio.uol.domain.Jogador
@import com.caio.uol.domain.enumeration.Time
@import java.util.List
@import java.util.Objects

@param Jogador jogador
@param List<Jogador> jogadores = null

<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>UOL HOST</title>
</head>
<body>
    <h1>Seja bem vindo</h1>

    <form method="POST" action="/jogador">
        <div>
            <label>
                Nome:
                <input type="text" name="nome"/>
            </label>
        </div>

        <label>
            Time:
            <select name="time">
                <option value="default" selected>Selecione</option>
                <option value="${Time.LIGA_DA_JUSTICA.getName()}">Liga da Justiça</option>
                <option value="${Time.VINGADORES.getName()}">Vingadores</option>
            </select>
        </label>
        <br/>
        <input type="submit" value="Cadastrar">
    </form>

    @if(Objects.nonNull(jogadores))
        <h3>Lista de jogadores</h3>
        <ul>
            @for(Jogador jog : jogadores)
                <li>${jog.getNome()} | ${jog.getCodinome()} | ${jog.getTime().getName()}</li>
            @endfor
        </ul>
    @endif

</body>
</html>