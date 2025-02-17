@import com.caio.uol.infra.integration.domain.LigaDaJustica
@import com.caio.uol.infra.integration.domain.Vingadores
@import com.caio.uol.infra.integration.domain.VingadoresWrapper
@import java.util.Objects

@param LigaDaJustica ligaDaJustica
@param VingadoresWrapper vingadoresWrapper

<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>UOL</title>
</head>
<body>
    @if(Objects.isNull(ligaDaJustica))
        <a  href="/liga-da-justica">Ver liga da justiça</a>
        <h1>Os Vingadores</h1>
    @endif

    @if(Objects.isNull(vingadoresWrapper))
        <a href="/vingadores">Ver vingadores</a>
        <h1>Liga da Justiça</h1>
    @endif

    
    @if(ligaDaJustica != null)
        <ul>
            @for(String codinome : ligaDaJustica.getCodinomes())
                <li>${codinome}</li>
            @endfor
        </ul>
    @endif

    @if(vingadoresWrapper != null)
        <ul>
            @for(Vingadores vinga : vingadoresWrapper.vingadores())
                <li>${vinga.codinome()}</li>
            @endfor
        </ul>
    @endif

</body>
</html>