package com.coinsthai.converter;

import com.coinsthai.pojo.intenum.BillType;
import com.coinsthai.util.CoinNumberUtils;
import com.coinsthai.vo.BillApiView;
import com.coinsthai.vo.CoinView;
import com.coinsthai.vo.MarketView;
import com.coinsthai.vo.bill.BillView;
import org.springframework.stereotype.Component;

/**
 * @author 
 */
@Component
public class BillApiFromViewConverter extends PriceVolumeConverter<BillApiView, BillView> {

    @Override
    protected String resolveMarketId(BillView source) {
        return source.getMarketId();
    }

    @Override
    protected void convertSpecials(BillView source, BillApiView target, MarketView market, CoinView baseCoin,
                                   CoinView targetCoin) {
        target.setPrice(CoinNumberUtils.formatDoubleVolume(source.getPrice(), baseCoin.getUnit()));
        target.setAveragePrice(CoinNumberUtils.formatDoubleVolume(source.getAveragePrice(), baseCoin.getUnit()));
        target.setVolume(CoinNumberUtils.formatDoubleVolume(source.getVolume(), targetCoin.getUnit()));
        target.setRemainVolume(CoinNumberUtils.formatDoubleVolume(source.getRemainVolume(), targetCoin.getUnit()));
        target.setDealValue(CoinNumberUtils.formatDoubleVolume(source.getDealValue(), baseCoin.getUnit()));
        target.setBrokerageRate(source.getBrokerageRate().doubleValue());

        if (source.getType() == BillType.BUY) {
            target.setBrokerage(CoinNumberUtils.formatDoubleVolume(source.getBrokerage(), targetCoin.getUnit()));
        }
        else {
            target.setBrokerage(CoinNumberUtils.formatDoubleVolume(source.getBrokerage(), baseCoin.getUnit()));
        }

        target.setMarketId(market.getId());
        target.setMarketName(market.getName());
    }
}
