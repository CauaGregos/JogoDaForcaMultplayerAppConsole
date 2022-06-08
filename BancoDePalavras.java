public class BancoDePalavras
{
    private static String[] palavras = 
    {
            "PNEUMOULTRAMICROSCOPICOSSILICOVULCANOCONIOSE",
            "PARACLOROBENZILPIRROLIDINONETILBENZIMIDAZOL",
            "PIPERIDINOETOXICARBOMETOXIBENZOFENONA",
            "DIMETILAMINOFENILDIMETILPIRAZOLONA",
            "HIPOPOTOMONSTROSESQUIPEDALIOFOBIA",
            "TETRABROMETACRESOLSULFONOFTALEINA",
            "MONOSIALOTETRAESOSILGANGLIOSIDEO",
            "ANTICONSTITUCIONALISSIMAMENTE",
            "OFTALMOTORRINOLARINGOLOGISTA",
            "ACETILPARAFENILENADIAMINICO",
            "COLPOCISTURETROCISTOSTOMICO",
            "ELECTROFOTOTERMOTERAPEUTICO",
            "DIMETATARSOQUARTIFALANGICO",
            "ALCOILBENZENOSSULFONATICO",
            "PNEUMOUTRAMICROSCOPICOSILICOVUCANOCONIOTICO",
            "INCONSTITUCIONALISIMAMENTE",
            "PARACLOROBENZILPIRROLIDINONETILBENZIMIDAZOL",
            "OFTALMOTORRINOLARINGOLOGISTA",
            "INTERDISCIPLINARIDADE"
    };

    public static Palavra getPalavraSorteada ()
    {
        Palavra palavra = null;

        try
        {
            palavra =
            new Palavra (BancoDePalavras.palavras[
            (int)(Math.random() * BancoDePalavras.palavras.length)]);
        }
        catch (Exception e)
        {}

        return palavra;
    }
}
