package com.example.ocinefilo

class RepositorioFilme {
    companion object {
        fun createDataSet(): ArrayList<FilmeModel> {
            val list = ArrayList<FilmeModel>()
            list.add(
                FilmeModel(
                    id = "1",
                    titulo = "O Segredo: Ouse Sonhar",
                    sinopse = "Inspirado no best-seller que vendeu mais de 30 milhões de cópias ao redor mundo, O Segredo: Ouse Sonhar traz a história de Miranda (Katie Holmes), uma viúva que luta para criar os três filhos sozinha. Após uma forte tempestade destruir sua casa, ela contrata Bray (Josh Lucas) para ajudá-la na reconstrução. Durante a obra, ele passa a compartilhar com Miranda sua filosofia de acreditar no poder do universo, na relação causa e efeito, passado e presente.",
                    imagem = "https://capas-m.imagemfilmes.com.br/164835_000_m.jpg"
                )
            )
            list.add(
                FilmeModel(
                    id = "2",
                    titulo = "Rambo: Até o Fim",
                    sinopse = "O tempo passou para Rambo (Sylvester Stallone), que agora vive recluso em um rancho. Sua vida marcada por lutas violentas ficou para trás, mas deixou marcas inesquecíveis. No entanto, quando uma jovem que ele ajudou a criar é sequestrada, Rambo precisará confrontar seu passado e resgatar suas habilidades de combate para enfrentar o mais perigoso cartel mexicano. A busca logo se transforma em uma caçada por justiça, onde nenhum criminoso será perdoado.",
                    imagem = "https://capas-m.imagemfilmes.com.br/164856_000_m.jpg"
                )
            )
            list.add(
                FilmeModel(

                    id = "3",
                    titulo = "Um dia de Chuva em Nova York",
                    sinopse = "Apaixonado por Nova York, Gatsby (Timothée Chalamet) decide passar um fim de semana na cidade ao lado de Ashleigh (Elle Fanning), sua namorada. No entanto, aquilo que era pra ser uma aventura romântica acaba tomando um rumo inesperado. Aspirante a jornalista, Ashleigh conhece o diretor de cinema Roland Pollard (Liev Schreiber), que a convida para a exibição de seu mais recente trabalho. Gatsby, por sua vez, encontra Chan (Selena Gomez), a irmã mais nova de sua ex-namorada, com quem passa o restante da viagem. Um dia de chuva em Nova York será o suficiente para fazer com que Ashleigh redescubra suas verdadeiras paixões e Gatsby aprenda que só se vive uma vez - mas que é o suficiente se for ao lado da pessoa certa.",
                    imagem = "https://capas-m.imagemfilmes.com.br/164862_000_m.jpg"
                )
            )
            return list
        }
    }
}