import io.eblock.eos4j.Ecc;
import io.eblock.eos4j.Rpc;
import io.eblock.eos4j.api.exception.ApiException;
import io.eblock.eos4j.api.vo.Block;
import io.eblock.eos4j.api.vo.ChainInfo;
import io.eblock.eos4j.api.vo.transaction.Transaction;
import io.eblock.eos4j.api.vo.transaction.push.Tx;
import io.eblock.eos4j.api.vo.transaction.push.TxAction;
import io.eblock.eos4j.api.vo.transaction.push.TxSign;
import io.eblock.eos4j.ese.Action;
import io.eblock.eos4j.ese.DataParam;
import io.eblock.eos4j.ese.DataType;
import io.eblock.eos4j.utils.ByteUtils;
import io.eblock.eos4j.utils.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.*;

public class Subscribe {


    public static void main(String[] args) throws Exception {

        Rpc rpc = new Rpc("https://test-chain.ambt.art"); // 测试节点url
        String pk = "5HqNwwsqth1ULSVf8EASvEfWg8bsASPT1KpkY8HiQuy6MzuUVDn";
        try {
            Transaction subscribeT = rpc.subscribe(pk,
                    "amax2booster",
                    "idoadmin",
                    "usera",
                    "0",
                    "idoadmin",
                    "10.00000000 AMAX",
                    "amax.token",
                    "2.00000000 AMAE",
                    "amae.token",
                    "admin"
            );
            System.out.println(subscribeT.getTransactionId());


            Transaction transaction = rpc.thirdUnlock(pk, "amax2custody", "idoadmin", "0", "1", "admin", "");
            System.out.println(transaction.getTransactionId());
        } catch (ApiException e) {
            e.printStackTrace();
            System.out.println(e.getError().getError().getWhat());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}