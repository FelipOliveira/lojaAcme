package com.br.foliveira.lojaACME;

import java.math.BigDecimal;
//import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.br.foliveira.lojaACME.model.Assinatura;
import com.br.foliveira.lojaACME.model.Cliente;
import com.br.foliveira.lojaACME.model.Pagamento;
import com.br.foliveira.lojaACME.model.Produto;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Iniciando Loja ACME");

        // 1
        Produto produto_1 = new Produto("prod_1", "path_1.png", new BigDecimal("4.99"));
        Produto produto_2 = new Produto("prod_2", "path_2.ogg", new BigDecimal("2.99"));
        Produto produto_3 = new Produto("prod_3", "path_3.vod", new BigDecimal("12.99"));

        Cliente cliente_1 = new Cliente("fulano");
        Cliente cliente_2 = new Cliente("ciclano");
        Cliente cliente_3 = new Cliente("beltrano");

        List<Produto> lista_1 = List.of(produto_2);
        List<Produto> lista_2 = List.of(produto_1, produto_2, produto_3);
        List<Produto> lista_3 = List.of(produto_1, produto_1);
        
        Pagamento pagamento_hoje = new Pagamento(lista_1, LocalDate.now(), cliente_1);
        Pagamento pagamento_ontem = new Pagamento(lista_3, LocalDate.now().minusDays(1), cliente_3);
        Pagamento pagamento_mes_passado = new Pagamento(lista_2, LocalDate.now().minusMonths(1), cliente_2);

        List<Pagamento> pagamentos = List.of(pagamento_hoje, pagamento_ontem, pagamento_mes_passado);
        
        // 2
        System.out.println("2 =========================\n" 
            + pagamentos.stream().sorted((p1, p2) -> p1.getDataCompra().compareTo(p2.getDataCompra())).collect(Collectors.toList()));

        // 3
        System.out.println("3 =========================");
        List<List<Produto>> lista = pagamentos.stream()
            .map(Pagamento::getProdutos)
            .collect(Collectors.toList());
        

        // 4
        System.out.println("4 =========================");
        Map<String, BigDecimal> somaValoresPagamento = pagamentos.stream()
            .collect(Collectors.groupingBy(p -> p.getCliente().getNome(),
                Collectors.reducing(BigDecimal.ZERO,
                    p -> p.getProdutos().stream()
                        .map(Produto::getPreco)
                        .reduce(BigDecimal.ZERO,
                        BigDecimal::add),
                    BigDecimal::add
                )));
        
        somaValoresPagamento.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);
        
        // 5
        System.out.println("5 =========================");
        

        // 6
        Map<Cliente, List<List<Produto>>> clienteParaProdutos = pagamentos.stream()
            .collect(Collectors.groupingBy(Pagamento::getCliente,
                Collectors.mapping(Pagamento::getProdutos, Collectors.toList())
            ));


        // 7
        System.out.println("7 =========================\n" + 
        somaValoresPagamento.entrySet().stream().max(Map.Entry.comparingByValue()));

        // 8
        System.out.println("8 =========================");
        Map<YearMonth, BigDecimal> valorPagamentosNoMes = pagamentos.stream()
            .collect(Collectors.groupingBy(p -> YearMonth.from(p.getDataCompra()),
                Collectors.reducing(BigDecimal.ZERO,
                    p -> p.getProdutos().stream()
                        .map(Produto::getPreco)
                        .reduce(BigDecimal.ZERO,
                        BigDecimal::add),
                    BigDecimal::add
                )));

        valorPagamentosNoMes.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);

        // 9
        final BigDecimal VALOR_MENSALIDADE = new BigDecimal(89.99);

        Assinatura assinatura_fulano = new Assinatura(VALOR_MENSALIDADE, LocalDateTime.of(2022, Month.JANUARY, 15, 0, 0), cliente_1, LocalDateTime.of(2023, Month.APRIL, 3, 0, 0));
        Assinatura assinatura_ciclano = new Assinatura(VALOR_MENSALIDADE, LocalDateTime.of(2023, Month.FEBRUARY, 22, 0, 0), cliente_2);
        Assinatura assinatura_beltrano = new Assinatura(VALOR_MENSALIDADE, LocalDateTime.of(2022, Month.DECEMBER, 10, 0, 0), cliente_3);

        List<Assinatura> assinaturas = List.of(assinatura_fulano, assinatura_ciclano, assinatura_beltrano);
        
        // 10
        System.out.println("10 ========================");
        long mesesAssinatura = ChronoUnit.MONTHS.between(assinatura_fulano.getInicio(), LocalDateTime.now());
        System.out.println(mesesAssinatura);

        // 11
        System.out.println("11 ========================");
        
        // 12
        
        
    }

}
