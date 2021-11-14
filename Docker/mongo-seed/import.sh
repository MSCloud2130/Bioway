#! /bin/bash
mongoimport --host mongo --db cart_db --collection cart --type json --file /data/test_data/cart/cart.json --jsonArray

mongoimport --host mongo --db customers --collection customer --type json --file /data/test_data/customers/customer.json --jsonArray

mongoimport --host mongo --db identity_db --collection user --type json --file /data/test_data/identity/user.json --jsonArray

mongoimport --host mongo --db payments --collection card --type json --file /data/test_data/payments/card.json --jsonArray
mongoimport --host mongo --db payments --collection payment --type json --file /data/test_data/payments/payment.json --jsonArray

mongoimport --host mongo --db products_db --collection product --type json --file /data/test_data/products/product.json --jsonArray

mongoimport --host mongo --db products_soap_db --collection product --type json --file /data/test_data/ProductSearch/soap-product.json --jsonArray

mongoimport --host mongo --db purchases --collection purchase --type json --file /data/test_data/purchases/purchase.json --jsonArray

mongoimport --host mongo --db suppliers_db --collection supplier --type json --file /data/test_data/suppliers/supplier.json --jsonArray