module tp.module.modxx {
	requires transitive tp.module.modzz;
	exports tp.mod.mod_xx.pub; 
	opens tp.mod.mod_xx.pub; 
	exports tp.mod.mod_xx_ext;
	requires transitive tp.module.modaa.itf;
	uses tp.mod.mod_aa.itf.MyDisplayApi;
}

// pour uses tp.mod.mod_aa.itf.MyDisplayApi;
// besoin de requires transitive tp.module.modaa.itf;
// mais pas besoin de  requires transitive tp.module.modaa.impl1 ou impl2;