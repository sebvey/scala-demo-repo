file://<WORKSPACE>/Framework/src/test/scala/io/sve/baseprj/framework/SparkSpec.scala
### scala.MatchError: implicit class <error> extends  (of class scala.reflect.internal.Trees$ClassDef)

occurred in the presentation compiler.

action parameters:
uri: file://<WORKSPACE>/Framework/src/test/scala/io/sve/baseprj/framework/SparkSpec.scala
text:
```scala
package io.sve.baseprj.framework

trait SparkSpec {

    self: BaseSpec =>
        implicit va

}

```



#### Error stacktrace:

```
scala.tools.nsc.typechecker.Unapplies.constrParamss(Unapplies.scala:64)
	scala.tools.nsc.typechecker.Unapplies.factoryMeth(Unapplies.scala:141)
	scala.tools.nsc.typechecker.Unapplies.factoryMeth$(Unapplies.scala:138)
	scala.meta.internal.pc.MetalsGlobal$MetalsInteractiveAnalyzer.factoryMeth(MetalsGlobal.scala:67)
	scala.tools.nsc.typechecker.MethodSynthesis$MethodSynth.enterImplicitWrapper(MethodSynthesis.scala:229)
	scala.tools.nsc.typechecker.MethodSynthesis$MethodSynth.enterImplicitWrapper$(MethodSynthesis.scala:228)
	scala.tools.nsc.typechecker.Namers$Namer.enterImplicitWrapper(Namers.scala:59)
	scala.tools.nsc.interactive.InteractiveAnalyzer$InteractiveNamer.enterExistingSym(Global.scala:94)
	scala.tools.nsc.interactive.InteractiveAnalyzer$InteractiveNamer.enterExistingSym$(Global.scala:80)
	scala.tools.nsc.interactive.InteractiveAnalyzer$$anon$2.enterExistingSym(Global.scala:51)
	scala.tools.nsc.typechecker.Namers$Namer.standardEnterSym(Namers.scala:306)
	scala.tools.nsc.typechecker.AnalyzerPlugins.pluginsEnterSym(AnalyzerPlugins.scala:479)
	scala.tools.nsc.typechecker.AnalyzerPlugins.pluginsEnterSym$(AnalyzerPlugins.scala:478)
	scala.meta.internal.pc.MetalsGlobal$MetalsInteractiveAnalyzer.pluginsEnterSym(MetalsGlobal.scala:67)
	scala.tools.nsc.typechecker.Namers$Namer.enterSym(Namers.scala:280)
	scala.tools.nsc.typechecker.Typers$Typer.enterSym(Typers.scala:1954)
	scala.tools.nsc.typechecker.Typers$Typer.$anonfun$enterSyms$1(Typers.scala:1949)
	scala.tools.nsc.typechecker.Typers$Typer.enterSyms(Typers.scala:1949)
	scala.tools.nsc.typechecker.Typers$Typer.typedTemplate(Typers.scala:2011)
	scala.tools.nsc.typechecker.Typers$Typer.typedClassDef(Typers.scala:1851)
	scala.tools.nsc.typechecker.Typers$Typer.typed1(Typers.scala:5733)
	scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:5812)
	scala.tools.nsc.typechecker.Typers$Typer.typedStat$1(Typers.scala:5876)
	scala.tools.nsc.typechecker.Typers$Typer.$anonfun$typedStats$10(Typers.scala:3356)
	scala.tools.nsc.typechecker.Typers$Typer.typedStats(Typers.scala:3356)
	scala.tools.nsc.typechecker.Typers$Typer.typedPackageDef$1(Typers.scala:5444)
	scala.tools.nsc.typechecker.Typers$Typer.typed1(Typers.scala:5736)
	scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:5812)
	scala.tools.nsc.typechecker.Analyzer$typerFactory$TyperPhase.apply(Analyzer.scala:114)
	scala.tools.nsc.Global$GlobalPhase.applyPhase(Global.scala:453)
	scala.tools.nsc.interactive.Global$TyperRun.$anonfun$applyPhase$1(Global.scala:1341)
	scala.tools.nsc.interactive.Global$TyperRun.applyPhase(Global.scala:1341)
	scala.tools.nsc.interactive.Global$TyperRun.typeCheck(Global.scala:1334)
	scala.tools.nsc.interactive.Global.typeCheck(Global.scala:666)
	scala.meta.internal.pc.PcCollector.<init>(PcCollector.scala:29)
	scala.meta.internal.pc.PcSemanticTokensProvider$Collector$.<init>(PcSemanticTokensProvider.scala:18)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector$lzycompute$1(PcSemanticTokensProvider.scala:18)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector(PcSemanticTokensProvider.scala:18)
	scala.meta.internal.pc.PcSemanticTokensProvider.provide(PcSemanticTokensProvider.scala:71)
	scala.meta.internal.pc.ScalaPresentationCompiler.$anonfun$semanticTokens$1(ScalaPresentationCompiler.scala:157)
```
#### Short summary: 

scala.MatchError: implicit class <error> extends  (of class scala.reflect.internal.Trees$ClassDef)