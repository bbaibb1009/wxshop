package com.wxshop.weixin;

import com.wxshop.util.Page;

public interface IWeiFuwuhaoService {

	public Page queryFuwuhao(WcWeiFuwuhao fuwuhao);
	public WcWeiFuwuhao getFuwuhaoById(Integer id);
	public void updFuwuhao(WcWeiFuwuhao fuwuhao_Q);
}
