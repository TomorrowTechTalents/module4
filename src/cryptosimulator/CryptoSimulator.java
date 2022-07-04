package cryptosimulator;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class CryptoSimulator {
    public static void main(String[] args) {
        LocalDate diaPrimeiro = LocalDate.of(2022, 6, 1);
        LocalDate diaSegundo = LocalDate.of(2022, 6, 2);
        LocalDate diaTerceiro = LocalDate.of(2022, 6, 3);

        final Currency btc = new Currency("btc", BigDecimal.valueOf(20_000));
        final Currency eth = new Currency("eth", BigDecimal.valueOf(10_000));
        final Currency luna = new Currency("luna", BigDecimal.valueOf(1_000));

        final List<Order> orders = Arrays.asList(new Order(4L, btc, diaPrimeiro),
                                                 new Order(1L, btc, diaSegundo),
                                                 new Order(2L, btc, diaTerceiro),
                                                 new Order(1L, btc, diaTerceiro),
                                                 new Order(2L, eth, diaPrimeiro),
                                                 new Order(8L, eth, diaTerceiro),
                                                 new Order(18L, luna, diaPrimeiro),
                                                 new Order(12L, luna, diaPrimeiro),
                                                 new Order(8L, luna, diaSegundo),
                                                 new Order(1L, luna, diaPrimeiro),
                                                 new Order(10L, luna, diaTerceiro)
        );

        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));

        BigDecimal total = getTotal(orders);
        System.out.println("total: " + nf.format(total));

        Map<String, BigDecimal> details = getTotalByCrypto(orders);
        System.out.println("detalhes: " + details);

        Map<String, String> details2 = getFormattedTotalByCrypto(orders);
        System.out.println("detalhes 2: " + details2);

        Map<String, Map<LocalDate, BigDecimal>> details3 = getTotalByCryptoDetailedByDate(orders);
        System.out.println("detalhes 3: " + details3);

        BigDecimal btcCompradosDia = details3.get("btc").get(LocalDate.of(2022, 6, 3));
        System.out.println("Btc comprados dia 03/06/2022: " + btcCompradosDia);
    }

    private static BigDecimal getTotal(List<Order> orders) {
        return orders.stream().
                      map(order -> {
                          BigDecimal amountAsBD = BigDecimal.valueOf(order.getAmount());
                          return order.getCurrency().getPrice().multiply(amountAsBD);
                      }).
                      reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static Map<String, BigDecimal> getTotalByCrypto(List<Order> orders) {
        return orders.stream().
                      collect(Collectors.toMap(order -> order.getCurrency().getName(),
                                               order -> order.getCurrency().getPrice().
                                                        multiply(BigDecimal.valueOf(order.getAmount())),
                                               BigDecimal::add
                      ));
    }

    private static Map<String, String> getFormattedTotalByCrypto(List<Order> orders) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));

        Map<String, BigDecimal> totalByCrypto =
            orders.
            stream().
            collect(Collectors.groupingBy(order -> order.getCurrency().getName(),
                                          Collectors.mapping(order -> order.getCurrency().getPrice().
                                                                      multiply(BigDecimal.valueOf(order.getAmount())),
                                                             Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)
                                          )
            ));

        return totalByCrypto.entrySet().stream().
                                        collect(Collectors.toMap(Map.Entry::getKey,
                                                                 entry -> nf.format(entry.getValue()))
                                        );
    }

    private static Map<String, Map<LocalDate, BigDecimal>> getTotalByCryptoDetailedByDate(List<Order> orders) {
        return orders.stream().
                      collect(Collectors.
                              groupingBy(order -> order.getCurrency().getName(),
                                         Collectors.toMap(Order::getDate,
                                                          order -> order.getCurrency().getPrice().
                                                                   multiply(BigDecimal.valueOf(order.getAmount())),
                                                          BigDecimal::add
                                         )
                      ));
    }
}
