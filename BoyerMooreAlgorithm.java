/*
 *      ASSIGNMENT 2 (Boyer-Moore Algorithm)
 * 
 * MEMBER 1: ANDYCLOS A/L BOON MEE (22300738)
 * MEMBER 2: MUHAMMAD EZZAT HAZIQ BIN ELHAN (22300604)
 * MEMBER 3: KAM WENG XUAN (22300683)
 * MEMBER 4: LOH WEI TING (22300549)
 * 
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BoyerMooreAlgorithm
{
    public static String findBoyerMoore(char[] text, char[] pattern) 
    {
        int n = text.length;        // length of the text
        int m = pattern.length;     // length of the pattern

        if (m == 0) 
        {
            return "The pattern is empty: " + 0;       // trivial search for empty string
        }

        // Bad Character Heuristic
        Map<Character, Integer> last = new HashMap<>();          // the 'last' map

        for (int i = 0; i < n; i++)
            last.put(text[i], -1);      // set -1 as default for all text characters

        for (int k = 0; k < m; k++)
            last.put(pattern[k], k);    // set the rightmost occurrence of each character in the pattern 

        // Good Suffix Heuristic
        int[] goodSuffixShift = new int[m + 1]; // good suffix shift
        int[] borderPosition = new int[m + 1];  // border position

        preprocessGoodSuffix(pattern, borderPosition, goodSuffixShift);     // preprocess the good suffix

        int badCharSpace = last.size() * (Character.BYTES + Integer.BYTES);  // Space for bad character table
        int goodSuffixSpace = goodSuffixShift.length * Integer.BYTES;        // Space for good suffix table
        int totalSpace = badCharSpace + goodSuffixSpace;                     // Total space

        System.out.println("Bad Character Table Space: " + badCharSpace + " bytes");
        System.out.println("Good Suffix Table Space: " + goodSuffixSpace + " bytes");
        System.out.println("Total Space: " + totalSpace + " bytes");
        
        int i = m - 1;    // i is the index of the end of the text

        while (i < n) 
        {
            int j = m - 1;  // j is the index of the end of the pattern

            while (j >= 0 && pattern[j] == text[i])     // if the characters match
            {
                i--;
                j--;
            }

            if (j < 0)  // if the entire pattern has been matched
            {
                return "Pattern found at index " + (i + 1);
            } 
            else    // if the characters do not match
            {
                int badCharacterShift = j - last.getOrDefault(text[i], -1);    // calculate the bad character shift
                int goodSuffixShiftValue = goodSuffixShift[j + 1];              // calculate the good suffix shift
                int shift = Math.max(badCharacterShift, goodSuffixShiftValue);  // calculate the shift

                i += shift;     // move i to the right
            }
        }

        return "No match found: " + -1; // no match found
    }

    private static void preprocessGoodSuffix(char[] pattern, int[] borderPosition, int[] goodSuffixShift)   // preprocess the good suffix
    {
        int m = pattern.length;     // length of the pattern
        int i = m;                  // Initialize i to the length of the pattern
        int j = m + 1;              // Initialize j to one position beyond the end of the pattern
        borderPosition[i] = j;      // Initialize the border position of the last character of the pattern

        while (i > 0) 
        {
            while (j <= m && pattern[i - 1] != pattern[j - 1])      // if the characters do not match
            {
                if (goodSuffixShift[j] == 0) 
                {
                    goodSuffixShift[j] = j - i;             // calculate the good suffix shift
                }
                
                j = borderPosition[j];      // move j to the border position
            }

            i--;            // move i to the left
            j--;            // move j to the left

            borderPosition[i] = j;      // set the border position of the character
        }

        j = borderPosition[0];

        for (i = 0; i <= m; i++) 
        {
            if (goodSuffixShift[i] == 0)
            {
                goodSuffixShift[i] = j;
            }

            if (i == j) 
            {
                j = borderPosition[j];
            }
        }
    }

    public static void main(String[] args) 
    {
        String text = "ctjfszswabtufcgrughcvadzpqlxoacdfroislogjolemzonavzfwkaxeirxxrldqgzkkndtksdcvfnpnetmmytiwoshqymmtgaionqwofwawyjdpunxdmolundenewsugacosbgoluqoprtlgooxqqbfuscutbxsdcmppexaysbnkcjurmtdqbiwnfnabvbnmmtptqnnghstyinlpmhcyamkqkclhaqqnwntikwwkwaoorgigppipsboljipsimxpkydiophrsytgtjxnphoicemkuvyusjyemunogrlwxkcldyduzcbdfeqdsksqchgkprdnyosgearokzbuopwwvobkrzjsafoobovxpejyylrodiqzxhksjnsvaxkdndurdupbykuqunruiwdhuhuubugxjmbgwwjpkypjzieflmexnxgswoheelsqafnweyidggtonwmjkauvvqxxauzqpudvgsxxkkedvxvhvoozijyayacvdumvqlimokqdcivjffeszhflldlhzlrbrhazdaxhyxkpfzmehksqfeverzybgkrmatlwxlwoztqcmlfezlwfjpimlrxmpuzbwcrqynvibckystmaweaprbbkoiispugiccvecfdahblftglbpetxolnhnnvyatlzqhlzzoygtrxtboxrbddfwwmpbdqggqfcwdzxuxlesjyxpkccioxslflhdfqixkyekwvncwpbsforhrjexrjiulafsrwzkhakxqwhdzpaetoqjlgebnbocfbxtdesngcrmuquulhmpcfgkpyfaqmumcoqyaljzfivocgqfmmffxbewxmndicjrrdgiltthulvierylolocvdwcwzkwhszgblzibplbderqschwyqbhnirmyrwqwomuahxvuhrjmjdkckrcrormfkijzjwwwrcmzhbamqbynvrhxxhpcwrewfkpbulodhooijfiwlswcaauegrwlmlhzsphrrixbveyeorjppsesfudbnyelywcnpgxlajmxdfmkxdmaouarhcytezeuxbupgmxsjthzcccicfmqowhgimmizepytabhkboobolfrfellvnzafpdeznojzvwtqwgaludeussluksozkirhpisosmizualpaevyhiipmuzuhlkqkcwnwkdisfteyornucyeqbnbmtibzirjcczpcmtxuvlmlgznlchpuhckqtuaaejmphuagbpzszoabbcspgldnmmlicmxazaimrkkhgkdiltctnhheztsqobbfiimpoxkazawsmfcougqynluoxfwecuuynezjlphrttjgacstgayblxykhyeqfcdwaqqrmaddozcaqckrhdkgseekeocdtntikpmqkypuzuzvbtbezjqvbvysjrqqudbujtcbolitpzkuiskkigzcditgvwpmkwgaptzkiobnlakmbtufrnrapsbkidkfxgyvbtworchdkowltjicdvaoatztgbglokngwdqaokvqehuyuygnkkmllksnbqhxjfkmmzihdxaqomqxjbdexwbavkykvycxgtgokvjeexskvqenyvfprwnljlizqdtpteydpznzbugwmskjovgtfubnkmkrnpqypjcvmkxfnqpsjjbposhdvjlzafkzwipnlhrvrwjlwdhnnhampqgqybutcqmqvsxwfukzhzhamgwovzbtndjqbpxamzkvzueplggbfginjhuqppfsohgxztycktdksukbseomujwfpffdkxnrstjpkgybabmfrtekdhcrqhkykawxkufiajssggrunrlgvnhtnqjddmrmxhhwgnutbfktwtdaopzfbiuyvcqpxnwjulgexwpmybcrasciqujgteqwgvrttaweqmloooxnzvgvgoqnbwwugvszpywahpuuhcrwdzysrkkdpmfigwgirfzixhhgzxxwcqsmgsfetaemrujikuxulqtojhoagbjiwltepaaztfqjsvzfotoyoqzdmzdrdqwknzhkvnyckllaqsjdtoyjcfirzigfzspzjvojxydlkfqwgbzstgcjqbkzcjffywhnfadqrkeiiqzskpxxzshwhodxwsynkchbmfqxbqsxjtsddglhukjuoktimuhaoffrultuvwrfctrkmdrktldtyntsrbwjvlcyyptpsdajxujgiboomqsxuzmshxcztczcsxivrmgcaemffrqpjnkgwbfdpdjoxrslrznbhgtrpuknfyvpdtorlkwazcwbnuhexnvwtwvssnmetnnpybpnvlyulcylrrpvxycxnjucfsxqpiweygkcuxqdpokkelockfkegsmpmnnnuvpfhjgiivrkaltkzkweriwkzzwysklwqvsmoamqenpohlcndbxrowctuurszvsqlnoqnxfgifvxtwqypiprjwmkchjtxzlxnzkhrqjhrnduoeuajyckqbcuaizilpyxqvpospnptsqutontnrrlpmkpfaukxxdwqqcsikzzgqcrhyijxznqomvcsdfwekfeipdygacohaicihxflbgicpcgxlqoujtrtmnjiycrxmuxjrlmjhepmibmssfjlbhbkzqtvyiaawmamrqunrwwdukjorwrfvbinsmhnmnmlxrlxjwlzoodkbractwwrkciyagqrfzoacxzpqfixykordauxoymskdpfzyyyuwaibrlfrirqhhgmvkaxqhxoljqlyfafszcmnlhqeprgrecxwmmesjwiucaddkuskouzryvujvlfxrgzeuygrkwuhwttnbppllzfilhrcjtgnmkuhqtwgrdtwfuwazmbualcbyntfjsskfngldapopyrgvfkhpmcnbhfwimblgzpexmsoqwgcetwdidjvcolbabuheuxuquhydcchrygwuqmvixshrsihnjptyileskedrvrqjzudlmjwsbghkukuxjrwxuahfrlhslggmdpfqwxouesrazsvntyzrmamyxobqjtyxosgrvlpxhmiumccrxzaghcfkxnxwyusgurpaoczjbsoglezjpsfpnlvhntfzmwovvondwbcsncexysadvhxupfnwyfzwnhnkbrmjrwhtxwsejmjxaeorobbpqmcbdnaugqsfoksbtgkwrrmpkzguloaigycdamkplqkrdsqbsjvsvdjnmjixpwefacnvterykkfwtxdwnbkevbehpyihpnffltijejeaahlvzikmuookeamqswydbgvkrirghkojniwffgkphqyzzjczaerauydygwgynqyaibumirvegfrliwwroxxmorgzguhpngvkjfjknmvgqlhnohfrrnrwyjhmnqdbrpijkgdqsbfuwinglbmwyancppkgxcwponjnwzwcwilbvaiwdgbaoddcyybapcldfugdndpgtvwtrutenzqznkcfirymwmfetlazkwgrhplxnmgdsagysrjkusvdfdhjqtfvruqyayxnclunakccbsfdimrqrpmmgeptqfsywpvesyowwkdzsyyvztwdjbdblkfvbrudwzypygcokzidsninzjzbmerwkfwcohvgqkbiqeinaftkkoofkpakbqmorbfxalrypmprajpctfmzhbwfqzvfivuukbbiucnajlfgzvfyrjkkufxcunjgtqofuztbtnsewzkfupcosjfhzxuqqdugjbcgxnvpbvqxhggcfrkkmgigaaotvisjehniximdcylhjbciuqcmuymchatfsukfwsckxgvxxfdzgcebyretwijvouqgvopsdcxtjspckkwesbkuxwqogzoniqvhqucactixdmmnhtrdycpeouqfoaazdccenwuvaoaifaycfpuuytnlyrnxzghncgdhilmkmvtcfoqwkwzriufachsngrsepmbzxvynlwxgvcdrfbazkcjulmrvmnaidkoubpcomcjdbjjcnituqilgrcdfggxbalotgtybsxduntrlhhfqpuctnshdvpjfpekyvhebmddaxphbdzptowcfypuudzzooarufzcormrsgkytcqzxzfmdjpxvbzqvsxsuawzgpsyfqicbbtsdsujuafreljqnarwcnrspbhqfrgqlbfiwcpsubprvkladkoudhiwahlbrytnonwtfklicavozzupysqqknrgvriqqlhqokgycrxpihpqhsudrljgrtfztbayrmmrmegrnoxlzmdkmnqutmtqqaeadsumcoafycwtebcazjnoabkwivevjtokwdskifkoyaxyywfvoodzgayfinabbdduajgdyaxuvfnltuttiwyhuesfzgyrajoqyrgqidrubleivutjtbtmrnmnxqulyuznvtgnguoykewnbzyqckhgwxeatocjhabvteumlipuunisipmgsrmpwemjhvkjwkczfdxhwedtwzqfvnwciixhfwxgajewoprjkcejfmjjusmxlarwewoqrcvfugfddswqodbthgezeivfpmiqlhxfzgbbdxmbhqruzxqastuofbwamsvnzfhkkylucmkyvrbajkyqcdswivgfbhrzrvkdzydtmumminjwfnkwmulxxxivqkquaciwgcpdclkkxwazhtkpafeebcmibusblkpouzsqpocxsucpcoifcimwjafsifjansodblgnwldwvvtbwazfucyocveofqaogkolshqjdcrnmotszsjqgwgjbzkbzaosvifnovgvqlzrvyoienzbgzzdqqqssyhspwmudsbzbcdhkeetyoqlnraoxjldexogzhmkqlhbdkklvendktvxtnafiofeataqcyeddimlxszqjnbzrxqgejdhsgqjtcikmgkbvithvriidgglkjafpyedrllagemvngrpwlbyzzdbimhuqxlidukfhhjamplibpqmnipdxzsipkbkvylhfkssfiijbphsrkqdxgadopvcdeetxdtynww";
        String pattern = "sfudbnyelywcnpgxlajmxdfmkxdmaouarhcytezeuxbupgmxsj";
        
        char[] text_arr = text.toCharArray();                           // text to search
        char[] pattern_arr = pattern.toCharArray();                     // pattern to search for
        long startTime = System.currentTimeMillis();                    // start the timer
        System.out.println(findBoyerMoore(text_arr, pattern_arr));      // print the result
        long endTime = System.currentTimeMillis();                      // end the timer
        long elapsed = endTime - startTime;                             // calculate the time taken to run the algorithm
        System.out.println("Text Size: " + text_arr.length);
        System.out.println("Pattern Size: " + pattern_arr.length);
                
        Set<Character> uniqueChars = new HashSet<>();                   // Calculate and print alphabet size
        
        for (char c : text.toCharArray()) 
        {
            uniqueChars.add(c);
        }

        System.out.println("Alphabet size: " + uniqueChars.size());
        System.out.println("Elapsed time: " + elapsed + " ms");         // print the time taken to run the algorithm
    }
}