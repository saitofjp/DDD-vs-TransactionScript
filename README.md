このリポジトリは

DDD風と、トランザクションスクリプト(従来)風の実装を比べるリポジトリです。

## まずコード

[DDD風のコードはこれ](https://github.com/saitofjp/DDD-vs-TransactionScript/blob/master/src/main/java/com/example/demo/service/Impl/OrderServiceDDDImpl.java#L44)

[トランザクションスクリプト(従来)風のコードはこれ](https://github.com/saitofjp/DDD-vs-TransactionScript/blob/master/src/main/java/com/example/demo/service/Impl/OrderServiceTransactionScriptImpl.java#L34)


## 見どころ

* トランザクションスクリプト風は、システムの複雑性に引きずられてしまう。
* トランザクションスクリプト風は、繰り返し要素を押し込めるリファクタをしようとしがち。
* DDD風は、if文のネストが無い。テストケースを洗うのが楽。

## ルール

### よくある要求

![要求](https://raw.githubusercontent.com/saitofjp/DDD-vs-TransactionScript/master/docs/requirements.png)

### よくあるシステム

![構成](https://raw.githubusercontent.com/saitofjp/DDD-vs-TransactionScript/master/docs/system-overview.png)
![詳細](https://raw.githubusercontent.com/saitofjp/DDD-vs-TransactionScript/master/docs/system-detail.png)
