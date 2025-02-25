@import com.caio.uol.domain.Jogador
@import com.caio.uol.web.dto.utils.Page
@import java.util.stream.Stream

@param Page<Jogador> playersPage = null


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
            position: relative;
            background-color: #a3afc9;
        }
        th span{
            position: absolute;
            right: .5rem;
            top: 40%;
            transform: translateY(-50%);
            font-size: 1.1rem;
            cursor: pointer;
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

        #btn-new-player{
            background:none;
            max-width: fit-content;
            margin: auto;
            padding: 8px 20px;
            cursor: pointer;
            margin-top: 10px;
            box-shadow: 3px 3px black;
            transition: .2s linear;
        }

        #btn-new-player:hover{
            box-shadow: none;
        }

        .btn-edit {
         position: relative;
         overflow: hidden;
         border: 1px solid #18181a;
         color: #18181a;
         display: inline-block;
         font-size: 15px;
         line-height: 15px;
         padding: 8px 20px;
         text-decoration: none;
         cursor: pointer;
         background: #fff;
         user-select: none;
         -webkit-user-select: none;
         touch-action: manipulation;
        }

        .btn-edit span:first-child {
         position: relative;
         transition: color 600ms cubic-bezier(0.48, 0, 0.12, 1);
         z-index: 10;
        }

        .btn-edit span:last-child {
         color: white;
         display: block;
         position: absolute;
         bottom: 0;
         transition: all 500ms cubic-bezier(0.48, 0, 0.12, 1);
         z-index: 100;
         opacity: 0;
         top: 50%;
         left: 50%;
         transform: translateY(225%) translateX(-50%);
         height: 14px;
         line-height: 13px;
        }

        .btn-edit:after {
         content: "";
         position: absolute;
         bottom: -50%;
         left: 0;
         width: 100%;
         height: 100%;
         background-color: black;
         transform-origin: bottom center;
         transition: transform 600ms cubic-bezier(0.48, 0, 0.12, 1);
         transform: skewY(9.3deg) scaleY(0);
         z-index: 50;
        }

        .btn-edit:hover:after {
         transform-origin: bottom center;
         transform: skewY(9.3deg) scaleY(2);
        }

        .btn-edit:hover span:last-child {
         transform: translateX(-50%) translateY(-50%);
         opacity: 1;
         transition: all 900ms cubic-bezier(0.48, 0, 0.12, 1);
        }

        .pagination-option{
            padding: 5px 15px;
            cursor: pointer;
        }

        .pagination-option:hover {
            color: white;
            background: #303030;
        }

    </style>
</head>
<body>
    <div class="content">
        <h3 style="text-align:center; margin: 0 0 20px 0">Lista de jogadores</h3>
        <table>
            <thead>
                <tr>
                    <th>
                        Nome
                        <span data-key="nome">&#8616;</span>
                    </th>
                    <th>
                        E-mail
                        <span data-key="email">&#8616;</span>
                    </th>
                    <th>Telefone</th>
                    <th>
                        Codinome
                        <span data-key="codinome">&#8616;</span>
                    </th>
                    <th>Grupo</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
            @for(Jogador jog : playersPage.content())
                <tr>
                    <td>${jog.getNome()}</td>
                    <td>${jog.getEmail()}</td>
                    <td>${jog.getTelefone()}</td>
                    <td style="display:flex; justify-content:space-between; align-items:center;">
                        ${jog.getCodinome()} <img width="40" height="40" style="object-fit:cover; object-position:top; border-radius:50%; box-shadow: 0px 0px 10px #a3afc9;" src="${jog.getHeroImageURL()}" alt="Herói miniatura">
                    </td>
                    <td>${jog.getTime().getName()}</td>
                    <td style="display:flex;justify-content:center; align-items:center; gap:.5rem; vertical-align:middle">
                        <button class="btn-edit" onclick="javascript:window.location.href='/cadastro/edit/${jog.getUuid().toString()}'"><span class="text">Editar</span><span>Editar!</span></button>
                        <button class="btn-edit btn-delete" onclick="deleteJogador('${jog.getUuid().toString()}')"><span class="text">Excluir</span><span>Excluir!</span></button>
                    </td>
                </tr>
            @else
                <tr>
                    <td colspan="7" style="text-align:center;">Sem jogadores cadastrados 🥴</td>
                </tr>
            @endfor
            </tbody>
        </table>

        <button id="btn-new-player" onclick="javascript:window.location.href='cadastro'">Novo jogador</button>
        <div style="display:flex; justify-content:center; margin-top: 1rem; position: relative;">
            <div style="position: absolute; left: 1rem">
                <label>Registros por página</label>
                <select style="border: none" name="pageSize" onchange="changePageSize()">
                    @for(int optionNumber : Stream.iterate(1, n -> !n.equals(playersPage.totalElements() + 1), n -> ++n).toList())
                        <option value="${optionNumber}">${optionNumber}</option>
                    @endfor
                </select>
                <div>
                    <span style="color: #545454">
                        Mostrando ${(playersPage.pageSize() * playersPage.pageNumber()) - playersPage.pageSize() + 1}
                        a ${(playersPage.pageSize() * playersPage.pageNumber()) - playersPage.pageSize() + playersPage.content().size()}
                        de ${playersPage.totalElements()}
                        registros
                    </span>
                </div>
            </div>

            @for(int i = 1; i <= playersPage.totalPages(); i++)
                <a class="pagination-option" data-key="${i}" onclick="changePageNumber('${i}')">${i}</a>
            @endfor
        </div>

    </div>

<script>
    const el = (e) => document.querySelector(e);
    const els = (e) => document.querySelectorAll(e);

    const actualUrlParams = new URL(window.location.href).search;
    const url = new URLSearchParams(actualUrlParams);

    async function deleteJogador(uuid){
        const req = await fetch('/players/' + uuid, {method: "DELETE"});
        window.location.href='';
    }

    function changePageNumber(pageNumber){
        if(${playersPage.pageNumber()} == pageNumber)
            return;

        url.set('pageNumber', pageNumber);
        redirect();
    }

    function changePageSize(){
        const pageSize = el('select').value;
        url.set('pageSize', pageSize);
        redirect();
    }

    function sortBy(field){
        url.set('sort', field);
        redirect();
    }

    function redirect(){
        window.location.href = window.location.origin + window.location.pathname + '?' + url.toString();
    }

    els('a[data-key]').forEach(pageNumberEl => {
        const key = pageNumberEl.getAttribute('data-key');
        if(key == ${playersPage.pageNumber()}){
            pageNumberEl.style.background = '#303030';
            pageNumberEl.style.color = 'white';
        }
    });

    els('option').forEach(option => {
        const pageSize = ${playersPage.pageSize()};
        console.log(pageSize);
        if(option.value == pageSize){
            option.selected = true;
        }
    });

    els('span[data-key]').forEach(sortName => {
        const key = sortName.getAttribute('data-key');
        if(url.get('sort') == key){
            sortName.style.color = 'green';
        }
        sortName.addEventListener('click', () => {
            if(url.get('sort') == key){
                url.delete('sort');
                redirect();
                return;
            }
            sortBy(key);
        });
    });
</script>
</body>
</html>