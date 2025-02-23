@import com.caio.uol.domain.Jogador

@param Jogador jogador

<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Cadastro jogador - UOL</title>
</head>
<body>

    <img width="500" src="${jogador.getHeroImageURL()}" alt="hero"/>

    <script>
        if(${jogador != null}){
            alert("Parabéns ${jogador.getNome()}, você é o ${jogador.getCodinome()} - ${jogador.getTime().getName()}!! 🥳🥳🥳")
        }

        setTimeout(() => {
            window.location.href=window.location.origin + '/players';
        }, 4000);
    </script>
</body>
</html>