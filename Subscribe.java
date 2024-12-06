import io.eblock.eos4j.Ecc;
import io.eblock.eos4j.Rpc;
import io.eblock.eos4j.api.exception.ApiException;
import io.eblock.eos4j.api.exception.ErrorDetails;
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
        String pk = "private key";
        try {
            Transaction subscribeT = rpc.subscribe(pk,
                    "amax2booster",
                    "idoadmin",
                    "usera",
                    "0",
                    "100.00000000 MUSE",
                    "muse.token",
                    "admin"
            );
            System.out.println("交易hash：" + subscribeT.getTransactionId());
        } catch (ApiException e) {
            System.err.println(e.getError().getError().getWhat());
            for (ErrorDetails detail : e.getError().getError().getDetails()) {
                System.err.println(detail.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}