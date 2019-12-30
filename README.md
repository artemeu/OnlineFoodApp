# Online Yemek Uygulaması


## Müşteri Tarafı (Web ReactJs)

Uygulamada müşteriler login olmadan yemekleri görüntüleyip ürünler hakkında bilgi sahibi olabilir


Müşteri sepetine yemekler ekliyebilir


Müşteri sepetindeki yemeklerden oluşan sipariş oluşturabilir


Müşteri daha önce verdiği siparişleri ve sipariş durumunu görüntüleyebilir


Müşteri sepete yemek eklemek istediğinde veya sipariş vermek istediğinde login olmak zorundadır


Login olurken beni hatırla butonuna tıklamış olan müşteriler login olduktan sonra token geçerli olduğu süre boyunca otomatik olarak login olur (tekrar login ekranına yönlendirilmez), token geçerliliği sona ermiş ise tekrar logine yönlendirilir.

## Çalışanlar Tarafı (Web ReactJs)
Uygulamada çalışanlar login olmadan hiç bir bilgiye erişemez

Restoran sahibi rolüyle sisteme giren kullanıcı yemekleri düzenleyebilir, fiyatlarını, içeriği vs değiştirebilir

Restoran sahibi rolüyle sisteme giren kullanıcı gelen siparişleri ve sipariş durumlarını görüntülüyebilir

Aşçı sisteme girdiğinde yalnızca gelen siparişleri görüntüleyebilmelidir

Aşçı rolüyle sisteme giren kullanıcı gelen siparişleri hazırlanıyor dan hazırlandı durumuna alıp teslimat aşamasına gonderir

Kurye rolüyle giriş yapan bir kullanıcı yalnızca teslimat aşamasında olan yemekleri ilgili kişiye götürüp sistem üzerinden siparişin durumunu sipariş teslim edildi aşamasına alır

Login olurken beni hatırla butonuna tıklamış olan çalışanlar login olduktan sonra token geçerli olduğu süre boyunca otomatik olarak login olur (tekrar login ekranına yönlendirilmez), token geçerliliği sona ermiş ise tekrar logine yönlendirilir.

## Müşteri Tarafı (Android)
Müşteriler login olmadan yemekleri görüntüleyebilir

Müşteriler kampanyalı yemeklere tıklayınca açılan detay ekranından veya yemek menüsünden yemekler sepete ekleyebilir

Müşteri sepetindeki yemeklerden oluşan sipariş oluşturabilir

Müşteri daha önce verdiği siparişleri ve sipariş durumunu görüntüleyebilir

Müşteri sepete yemek eklemek istediğinde veya sipariş vermek istediğinde login olmak zorundadır

## Backend
Spring Boot Rest Servis Uygulamasıdır
(spring boot/hibernate/oracledb kullanıldı)


## ReactJs

[npm](https://docs.npmjs.com/cli/install) paket yöneticisini kullanarak onlinefoodappfrntend ve onlinefoodappclient projelerine paket kurulumunu aşağıdaki gibi yapılır.

```bash
npm install
```

## Veri Tabanı

Veri tabanı script`leri OnlineFoodAppTables.sql dosyasının içinde

## Backend
onlinefoodappbckend projesinde application.properties içerisinde oracle veri tabanı kullanıcı adını ve şifrenizi yazın
```bash
spring.datasource.username=kullanıcıAdı
spring.datasource.password=sifre
```


## Deployment

1 - onlinefoodappbckend çalıştır

2- onlinefoodappmobile çalıştır

3 - [npm](https://docs.npmjs.com/cli/start.html) paket yöneticisini kullanarak onlinefoodappfrntend ve onlinefoodappclient uygulamaları aşağıdaki komut ile çalıştır.

onlinefoodappfrntend personel uygulaması port = 5000

onlinefoodappclient müşteri uygulaması port = 5500


```bash
npm start
```

## Personel Kullanıcı Bilgileri localhost:5000

Admin Kullanıcı Adı: mesutcan Şifre:123

Aşçı Kullanıcı Adı: cook Şifre:123

Kurye Kullanıcı Adı: courier Şifre:123

## Müşteri Kullanıcı Bilgileri localhost:5500

Müşteri Kullanıcı Adı: artemeu Şifre:123
