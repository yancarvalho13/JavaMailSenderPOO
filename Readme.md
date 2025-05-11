# 📬 Mail-Sender (mini-lib)

> **Objetivo:** disparar e-mails em **3 linhas** sem se preocupar com SMTP.  
> **Ideia-chave:** Arquitetura **Ports & Adapters** → seu código fala só com *interfaces*; troca de provedor sem tocar no core.

---

## 1. Como as peças se encaixam

```
Você → EmailSenderUseCase 🔌  (porta de entrada)
       → SendEmailService 🚦  (lógica)
          → EmailSenderPort 🔄 (porta de saída)
             → SimpleJavaMailAdapter ✈️ → Gmail / Mailtrap ☁️
```

* **Portas** = contratos (interfaces).  
* **Adapters** = “plugues” que atendem ao contrato.  
  Troque ✈️ SMTP por ✈️ SendGrid sem mexer em 🚦 nem no seu código.

---

## 2. Uso Rápido

```java
// Lê SMTP_* do .env (ou export)
Mailer mailer = MailerBuilder.withSMTPServerEnv().buildMailer();

EmailSenderUseCase sender =
        EmailModuleConfig.simpleJavaMail(mailer);

Email email = new Email(
        EmailAddress.of("no-reply@demo.com"),
        List.of(EmailAddress.of("destino@exemplo.com")),
        "Olá 👋",
        "<h2>Funcionou!</h2>");

sender.send(email, Optional.empty());
```

---

## 3. Passos para testar

1. Clone & entre no projeto.  
2. Crie `.env` com:

   ```
   SMTP_HOST=smtp.mailtrap.io
   SMTP_PORT=587
   SMTP_USER=seuUser
   SMTP_PASS=suaSenha
   ```

   *(Para Gmail use senha de app e host `smtp.gmail.com`)*  
3. `./gradlew run --args='br.com.seuemail.demo.Demo'`  
4. Veja o e-mail na caixa. ✅

---

### TL;DR Ports & Adapters

| Camada | Depende de | Muda quando… |
|--------|------------|--------------|
| **Domínio** (`Email`) | nada | regra de negócio mudar |
| **Porta IN** (`EmailSenderUseCase`) | domínio | API pública mudar |
| **Serviço** (`SendEmailService`) | portas | fluxo mudar |
| **Porta OUT** (`EmailSenderPort`) | domínio | contrato externo mudar |
| **Adapter** (SMTP, SendGrid…) | bibliotecas externas | provedor mudar |

> **Desacoplamento garantido:** pode refatorar ou trocar ✈️ que o restante continua intacto.