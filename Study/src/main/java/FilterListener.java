// Generated from /home/lihong/Workspace/SourceCode/opensource/thallo/Study/src/main/resources/Filter.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FilterParser}.
 */
public interface FilterListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FilterParser#r}.
	 * @param ctx the parse tree
	 */
	void enterR(FilterParser.RContext ctx);
	/**
	 * Exit a parse tree produced by {@link FilterParser#r}.
	 * @param ctx the parse tree
	 */
	void exitR(FilterParser.RContext ctx);
	/**
	 * Enter a parse tree produced by {@link FilterParser#pair}.
	 * @param ctx the parse tree
	 */
	void enterPair(FilterParser.PairContext ctx);
	/**
	 * Exit a parse tree produced by {@link FilterParser#pair}.
	 * @param ctx the parse tree
	 */
	void exitPair(FilterParser.PairContext ctx);
	/**
	 * Enter a parse tree produced by {@link FilterParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFilter(FilterParser.FilterContext ctx);
	/**
	 * Exit a parse tree produced by {@link FilterParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFilter(FilterParser.FilterContext ctx);
	/**
	 * Enter a parse tree produced by {@link FilterParser#not}.
	 * @param ctx the parse tree
	 */
	void enterNot(FilterParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by {@link FilterParser#not}.
	 * @param ctx the parse tree
	 */
	void exitNot(FilterParser.NotContext ctx);
	/**
	 * Enter a parse tree produced by {@link FilterParser#op}.
	 * @param ctx the parse tree
	 */
	void enterOp(FilterParser.OpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FilterParser#op}.
	 * @param ctx the parse tree
	 */
	void exitOp(FilterParser.OpContext ctx);
}