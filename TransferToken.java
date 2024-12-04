import io.eblock.eos4j.Ecc;
import io.eblock.eos4j.Rpc;
import io.eblock.eos4j.api.vo.Block;
import io.eblock.eos4j.api.vo.ChainInfo;
import io.eblock.eos4j.api.vo.transaction.Transaction;
import io.eblock.eos4j.api.vo.transaction.push.Tx;
import io.eblock.eos4j.api.vo.transaction.push.TxAction;
import io.eblock.eos4j.api.vo.transaction.push.TxSign;
import io.eblock.eos4j.ese.Action;
import io.eblock.eos4j.ese.DataParam;
import io.eblock.eos4j.ese.DataType;

import java.text.SimpleDateFormat;
import java.util.*;

public class TransferToken {


    public static void main(String[] args) throws Exception {

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        String pk = "your private key";

        Rpc rpc = new Rpc("https://test-chain.ambt.art"); // 测试节点url

        String from = "amaxdemouser"; // 转出账号
        String to = "testuser1";// 转入账号
        String quantity = "0.10000000 AMAX";
        String memo = "";

        ChainInfo info = rpc.getChainInfo();

        Tx tx = new Tx();
        Block block = rpc.getBlock(info.getLastIrreversibleBlockNum().toString());

        tx.setExpiration(info.getHeadBlockTime().getTime() / 1000L + 60L);
        tx.setRef_block_num(info.getLastIrreversibleBlockNum());
        tx.setRef_block_prefix(block.getRefBlockPrefix());
        tx.setNet_usage_words(0L);
        tx.setMax_cpu_usage_ms(0L);
        tx.setDelay_sec(0L);

        List<TxAction> actions = new ArrayList();
        tx.setActions(actions);
        Map<String, Object> map = new LinkedHashMap();
        map.put("from", from);
        map.put("to", to);
        map.put("quantity", new DataParam(quantity, DataType.asset, Action.transfer).getValue()); // 数量
        map.put("memo", memo);

        String contract = "amax.token";
        String actionName = "transfer";

        TxAction transferAction = new TxAction(from, contract, actionName, map);
        actions.add(transferAction);

        String sign = Ecc.signTransaction(pk, new TxSign(info.getChainId(), tx));

        String data = Ecc.parseTransferData(from, to, quantity, memo);
        transferAction.setData(data);
        tx.setExpiration(dateFormatter.format(new Date(1000L * Long.parseLong(tx.getExpiration().toString()))));
        Transaction transaction = rpc.pushTransaction("none", tx, new String[]{sign});

        System.out.println(transaction.getTransactionId());
    }
}